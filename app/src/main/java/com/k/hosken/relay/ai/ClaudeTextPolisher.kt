package com.k.hosken.relay.ai

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object ClaudeTextPolisher {

    private const val ENDPOINT = "https://api.anthropic.com/v1/messages"
    private const val ANTHROPIC_VERSION = "2023-06-01"
    private const val MODEL = "claude-opus-5"

    private const val SYSTEM_PROMPT =
        "You are a writing assistant that polishes short messages that were " +
            "transcribed from speech. Improve grammar, clarity, and readability " +
            "while preserving the original meaning, tone, and intent. Reply with " +
            "only the polished message text - no preamble, explanation, or quotation marks."

    suspend fun polish(apiKey: String, text: String): Result<String> =
        withContext(Dispatchers.IO) {

            if (apiKey.isBlank()) {
                return@withContext Result.failure(
                    IllegalStateException("No Claude API key set. Add one in Settings.")
                )
            }

            if (text.isBlank()) {
                return@withContext Result.failure(
                    IllegalStateException("There's no text to polish yet.")
                )
            }

            var connection: HttpURLConnection? = null

            try {
                val requestBody = JSONObject().apply {
                    put("model", MODEL)
                    put("max_tokens", 1024)
                    put("system", SYSTEM_PROMPT)
                    put(
                        "output_config",
                        JSONObject().put("effort", "low")
                    )
                    put(
                        "messages",
                        JSONArray().put(
                            JSONObject()
                                .put("role", "user")
                                .put("content", text)
                        )
                    )
                }

                connection = (URL(ENDPOINT).openConnection() as HttpURLConnection).apply {
                    requestMethod = "POST"
                    doOutput = true
                    connectTimeout = 20_000
                    readTimeout = 30_000
                    setRequestProperty("x-api-key", apiKey)
                    setRequestProperty("anthropic-version", ANTHROPIC_VERSION)
                    setRequestProperty("content-type", "application/json")
                }

                OutputStreamWriter(connection.outputStream, StandardCharsets.UTF_8).use {
                    it.write(requestBody.toString())
                }

                val responseCode = connection.responseCode

                val bodyStream =
                    if (responseCode in 200..299) connection.inputStream else connection.errorStream

                val responseText =
                    bodyStream?.bufferedReader(StandardCharsets.UTF_8)?.use { it.readText() }
                        ?: ""

                if (responseCode !in 200..299) {
                    val message = runCatching {
                        JSONObject(responseText)
                            .getJSONObject("error")
                            .getString("message")
                    }.getOrDefault("Claude API request failed ($responseCode)")

                    return@withContext Result.failure(IOException(message))
                }

                val content = JSONObject(responseText).getJSONArray("content")

                val polishedText = StringBuilder()
                for (i in 0 until content.length()) {
                    val block = content.getJSONObject(i)
                    if (block.optString("type") == "text") {
                        polishedText.append(block.optString("text"))
                    }
                }

                if (polishedText.isEmpty()) {
                    Result.failure(IOException("Claude returned an empty response."))
                } else {
                    Result.success(polishedText.toString().trim())
                }
            } catch (e: IOException) {
                Result.failure(e)
            } finally {
                connection?.disconnect()
            }
        }
}
