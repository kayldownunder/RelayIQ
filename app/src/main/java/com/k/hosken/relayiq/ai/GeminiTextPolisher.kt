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

object GeminiTextPolisher : TextPolisher {

    private const val MODEL = "gemini-1.5-flash"
    private const val ENDPOINT =
        "https://generativelanguage.googleapis.com/v1beta/models/$MODEL:generateContent"

    private const val SYSTEM_PROMPT =
        "You are a writing assistant that polishes short messages that were " +
            "transcribed from speech. Improve grammar, clarity, and readability " +
            "while preserving the original meaning, tone, and intent. Reply with " +
            "only the polished message text - no preamble, explanation, or quotation marks."

    override suspend fun polish(apiKey: String, text: String): Result<String> =
        withContext(Dispatchers.IO) {

            if (apiKey.isBlank()) {
                return@withContext Result.failure(
                    IllegalStateException("No Gemini API key set. Add one in Settings.")
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
                    put(
                        "systemInstruction",
                        JSONObject().put(
                            "parts",
                            JSONArray().put(JSONObject().put("text", SYSTEM_PROMPT))
                        )
                    )
                    put(
                        "contents",
                        JSONArray().put(
                            JSONObject().put(
                                "parts",
                                JSONArray().put(JSONObject().put("text", text))
                            )
                        )
                    )
                }

                connection = (URL(ENDPOINT).openConnection() as HttpURLConnection).apply {
                    requestMethod = "POST"
                    doOutput = true
                    connectTimeout = 20_000
                    readTimeout = 30_000
                    setRequestProperty("x-goog-api-key", apiKey)
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
                    }.getOrDefault("Gemini API request failed ($responseCode)")

                    return@withContext Result.failure(IOException(message))
                }

                val polishedText = JSONObject(responseText)
                    .getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .optString("text")
                    .trim()

                if (polishedText.isEmpty()) {
                    Result.failure(IOException("Gemini returned an empty response."))
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
