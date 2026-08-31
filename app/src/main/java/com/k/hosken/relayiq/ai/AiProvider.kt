package com.k.hosken.relayiq.ai

enum class AiProvider(
    val displayName: String,
    val keyPlaceholder: String,
    val polisher: TextPolisher
) {
    ANTHROPIC("Anthropic Claude", "sk-ant-...", ClaudeTextPolisher),
    OPENAI("OpenAI ChatGPT", "sk-...", OpenAiTextPolisher),
    GEMINI("Google Gemini", "AIza...", GeminiTextPolisher)
}
