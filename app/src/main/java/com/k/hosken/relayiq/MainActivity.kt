package com.k.hosken.relayiq

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.k.hosken.relayiq.ai.AiProvider
import com.k.hosken.relayiq.messaging.MessageSender
import com.k.hosken.relayiq.messaging.OtherAppsSender
import com.k.hosken.relayiq.messaging.TeamsSender
import com.k.hosken.relayiq.messenger.MessengerSender
import com.k.hosken.relayiq.ui.screens.ApiProviderScreen
import com.k.hosken.relayiq.ui.screens.HomeScreen
import com.k.hosken.relayiq.ui.screens.SettingsScreen
import com.k.hosken.relayiq.ui.theme.colorOptions
import com.k.hosken.relayiq.ui.theme.fontOptions
import kotlinx.coroutines.launch
import java.util.Locale

private enum class Screen {
    Home,
    Settings,
    ApiProvider
}

class MainActivity : ComponentActivity() {

    private var messageText by mutableStateOf("")

    private var currentScreen by mutableStateOf(Screen.Home)

    private var textSizeSp by mutableFloatStateOf(17f)
    private var fontName by mutableStateOf(fontOptions.first().name)
    private var colorName by mutableStateOf(colorOptions.first().name)
    private var selectedProvider by mutableStateOf(AiProvider.ANTHROPIC)
    private var apiKey by mutableStateOf("")
    private var isApiKeyFieldExpanded by mutableStateOf(false)
    private var isPolishing by mutableStateOf(false)

    private val speechLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            val results =
                result.data?.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )

            if (!results.isNullOrEmpty()) {
                messageText = results[0]
            }
        }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        textSizeSp = AppPreferences.getTextSize(this, textSizeSp)
        fontName = AppPreferences.getFontName(this, fontName)
        if (fontOptions.none { it.name == fontName }) {
            fontName = fontOptions.first().name
            AppPreferences.setFontName(this, fontName)
        }
        colorName = AppPreferences.getColorName(this, colorName)
        selectedProvider = AppPreferences.getSelectedProvider(this)
        apiKey = AppPreferences.getApiKey(this, selectedProvider)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.RECORD_AUDIO
            ),
            100
        )

        setContent {

            BackHandler(
                enabled = currentScreen != Screen.Home
            ) {
                currentScreen = when (currentScreen) {
                    Screen.ApiProvider -> Screen.Settings
                    else -> Screen.Home
                }
            }

            when (currentScreen) {

                Screen.Home -> HomeScreen(
                    message = messageText,

                    onMessageChange = {
                        messageText = it
                    },

                    onSpeakClick = {
                        startVoiceRecognition()
                    },

                    onPolishClick = {
                        polishMessage()
                    },

                    isPolishing = isPolishing,

                    onClearClick = {
                        messageText = ""
                    },

                    onSmsClick = {
                        MessageSender.sendSms(
                            this,
                            messageText
                        )
                    },

                    onWhatsAppClick = {
                        MessageSender.sendWhatsApp(
                            this,
                            messageText
                        )
                    },

                    onMessengerClick = {
                        MessengerSender.sendMessenger(
                            this,
                            messageText
                        )
                    },

                    onTeamsClick = {
                        TeamsSender.sendTeams(
                            this,
                            messageText
                        )
                    },

                    onOtherAppsClick = {
                        OtherAppsSender.send(
                            this,
                            messageText
                        )
                    },

                    onEmailClick = {
                        MessageSender.sendEmail(
                            this,
                            messageText
                        )
                    },

                    onSettingsClick = {
                        currentScreen = Screen.Settings
                    },

                    messageFontSizeSp = textSizeSp,
                    messageFontFamily = fontOptions.firstOrNull { it.name == fontName }?.family
                        ?: fontOptions.first().family,
                    messageTextColor = colorOptions.first { it.name == colorName }.color
                )

                Screen.Settings -> SettingsScreen(
                    textSizeSp = textSizeSp,
                    onTextSizeChange = {
                        textSizeSp = it
                        AppPreferences.setTextSize(this, it)
                    },
                    apiKey = apiKey,
                    onApiKeyChange = {
                        apiKey = it
                        AppPreferences.setApiKey(this, selectedProvider, it)
                    },
                    selectedProvider = selectedProvider,
                    isApiKeyFieldExpanded = isApiKeyFieldExpanded,
                    onApiKeyLockClick = {
                        currentScreen = Screen.ApiProvider
                    },
                    selectedFontName = fontName,
                    onFontChange = {
                        fontName = it
                        AppPreferences.setFontName(this, it)
                    },
                    selectedColorName = colorName,
                    onColorChange = {
                        colorName = it
                        AppPreferences.setColorName(this, it)
                    },
                    onBackClick = {
                        currentScreen = Screen.Home
                    }
                )

                Screen.ApiProvider -> ApiProviderScreen(
                    selectedProvider = selectedProvider,
                    onProviderClick = { provider ->
                        selectedProvider = provider
                        AppPreferences.setSelectedProvider(this, provider)
                        apiKey = AppPreferences.getApiKey(this, provider)
                        isApiKeyFieldExpanded = true
                        currentScreen = Screen.Settings
                    },
                    onBackClick = {
                        currentScreen = Screen.Settings
                    }
                )
            }
        }
    }

    private fun polishMessage() {

        if (apiKey.isBlank()) {
            Toast.makeText(
                this,
                "Add your ${selectedProvider.displayName} API key in Settings first",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (isPolishing) return

        isPolishing = true

        lifecycleScope.launch {

            val result = selectedProvider.polisher.polish(apiKey, messageText)

            result.onSuccess {
                messageText = it
            }.onFailure {
                Toast.makeText(
                    this@MainActivity,
                    it.message ?: "Couldn't polish the message",
                    Toast.LENGTH_LONG
                ).show()
            }

            isPolishing = false
        }
    }

    private fun startVoiceRecognition() {

        val intent =
            Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH
            )

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        intent.putExtra(
            RecognizerIntent.EXTRA_PREFER_OFFLINE,
            false
        )

        intent.putExtra(
            RecognizerIntent.EXTRA_PARTIAL_RESULTS,
            true
        )

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )

        speechLauncher.launch(intent)
    }
}
