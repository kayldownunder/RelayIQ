package com.k.hosken.relayiq.ai

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object OpenAiTextPolisher : TextPolisher {

    private const val ENDPOINT = "https://api.openai.com/v1/chat/completions"
    private const val MODEL = "gpt-4o-mini"

    private const val SYSTEM_PROMPT =
        "You are a writing assistant that polishes short messages that were " +
            "transcribed from speech. Improve grammar, clarity, and readability " +
            "while preserving the original meaning, tone, and intent. Reply with " +
            "only the polished message text - no preamble, explanation, or quotation marks."

    override suspend fun polish(apiKey: String, text: String): Result<String> =
        withContext(Dispatchers.IO) {

            if (apiKey.isBlank()) {
                return@withContext Result.failure(
                    IllegalStateException("No OpenAI API key set. Add one in Settings.")
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
                    put(
                        "messages",
                        JSONArray()
                            .put(
                                JSONObject()
                                    .put("role", "system")
                                    .put("content", SYSTEM_PROMPT)
                            )
                            .put(
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
                    setRequestProperty("Authorization", "Bearer $apiKey")
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
                    }.getOrDefault("OpenAI API request failed ($responseCode)")

                    return@withContext Result.failure(IOException(message))
                }

                val polishedText = JSONObject(responseText)
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .optString("content")
                    .trim()

                if (polishedText.isEmpty()) {
                    Result.failure(IOException("OpenAI returned an empty response."))
                } else {
                    Result.success(polishedText)
                }
            } catch (e: IOException) {
                Result.failure(e)
            } finally {
                connection?.disconnect()
            }
        }
}
