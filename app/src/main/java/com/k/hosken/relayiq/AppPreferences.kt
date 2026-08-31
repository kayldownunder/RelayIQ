package com.k.hosken.relayiq

import android.content.Context
import androidx.core.content.edit
import com.k.hosken.relayiq.ai.AiProvider

object AppPreferences {

    private const val PREFS_NAME = "relayiq_settings"
    private const val KEY_TEXT_SIZE = "text_size_sp"
    private const val KEY_FONT_NAME = "font_name"
    private const val KEY_COLOR_NAME = "color_name"
    private const val KEY_SELECTED_PROVIDER = "selected_ai_provider"

    // Kept in its own prefs file, excluded from backup (see data_extraction_rules.xml
    // and backup_rules.xml), so API keys never survive an app reinstall or device
    // transfer - each install requires the user to enter their own keys.
    private const val API_KEY_PREFS_NAME = "relayiq_api_key_prefs"
    private const val KEY_CLAUDE_API_KEY = "claude_api_key"
    private const val KEY_OPENAI_API_KEY = "openai_api_key"
    private const val KEY_GEMINI_API_KEY = "gemini_api_key"

    fun getTextSize(context: Context, default: Float): Float =
        prefs(context).getFloat(KEY_TEXT_SIZE, default)

    fun setTextSize(context: Context, value: Float) {
        prefs(context).edit { putFloat(KEY_TEXT_SIZE, value) }
    }

    fun getFontName(context: Context, default: String): String =
        prefs(context).getString(KEY_FONT_NAME, default) ?: default

    fun setFontName(context: Context, value: String) {
        prefs(context).edit { putString(KEY_FONT_NAME, value) }
    }

    fun getColorName(context: Context, default: String): String =
        prefs(context).getString(KEY_COLOR_NAME, default) ?: default

    fun setColorName(context: Context, value: String) {
        prefs(context).edit { putString(KEY_COLOR_NAME, value) }
    }

    fun getSelectedProvider(context: Context): AiProvider {
        val name = prefs(context).getString(KEY_SELECTED_PROVIDER, null)
        return AiProvider.entries.firstOrNull { it.name == name } ?: AiProvider.ANTHROPIC
    }

    fun setSelectedProvider(context: Context, provider: AiProvider) {
        prefs(context).edit { putString(KEY_SELECTED_PROVIDER, provider.name) }
    }

    fun getApiKey(context: Context, provider: AiProvider): String =
        apiKeyPrefs(context).getString(prefKeyFor(provider), "") ?: ""

    fun setApiKey(context: Context, provider: AiProvider, value: String) {
        apiKeyPrefs(context).edit { putString(prefKeyFor(provider), value) }
    }

    private fun prefKeyFor(provider: AiProvider): String =
        when (provider) {
            AiProvider.ANTHROPIC -> KEY_CLAUDE_API_KEY
            AiProvider.OPENAI -> KEY_OPENAI_API_KEY
            AiProvider.GEMINI -> KEY_GEMINI_API_KEY
        }

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun apiKeyPrefs(context: Context) =
        context.getSharedPreferences(API_KEY_PREFS_NAME, Context.MODE_PRIVATE)
}
