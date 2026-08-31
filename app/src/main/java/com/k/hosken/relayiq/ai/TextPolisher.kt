package com.k.hosken.relayiq.ai

interface TextPolisher {
    suspend fun polish(apiKey: String, text: String): Result<String>
}
