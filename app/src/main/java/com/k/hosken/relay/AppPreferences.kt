package com.k.hosken.relay

import android.content.Context
import androidx.core.content.edit

object AppPreferences {

    private const val PREFS_NAME = "relay_settings"
    private const val KEY_TEXT_SIZE = "text_size_sp"
    private const val KEY_FONT_NAME = "font_name"
    private const val KEY_COLOR_NAME = "color_name"

    // Kept in its own prefs file, excluded from backup (see data_extraction_rules.xml
    // and backup_rules.xml), so the API key never survives an app reinstall or device
    // transfer - each install requires the user to enter their own key.
    private const val API_KEY_PREFS_NAME = "relay_api_key_prefs"
    private const val KEY_CLAUDE_API_KEY = "claude_api_key"

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

    fun getClaudeApiKey(context: Context): String =
        apiKeyPrefs(context).getString(KEY_CLAUDE_API_KEY, "") ?: ""

    fun setClaudeApiKey(context: Context, value: String) {
        apiKeyPrefs(context).edit { putString(KEY_CLAUDE_API_KEY, value) }
    }

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun apiKeyPrefs(context: Context) =
        context.getSharedPreferences(API_KEY_PREFS_NAME, Context.MODE_PRIVATE)
}
