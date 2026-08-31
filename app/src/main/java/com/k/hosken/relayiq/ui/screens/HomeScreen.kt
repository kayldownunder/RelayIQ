package com.k.hosken.relayiq.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k.hosken.relayiq.ui.components.ActionButtons
import com.k.hosken.relayiq.ui.components.ClearButton
import com.k.hosken.relayiq.ui.components.Header
import com.k.hosken.relayiq.ui.components.MessageCard
import com.k.hosken.relayiq.ui.components.MicrophoneButton
import com.k.hosken.relayiq.ui.components.PolishButton
import com.k.hosken.relayiq.ui.theme.RelayIQBackground

@Composable
fun HomeScreen(
    message: String,
    onMessageChange: (String) -> Unit,
    onSpeakClick: () -> Unit,
    onPolishClick: () -> Unit,
    isPolishing: Boolean,
    onClearClick: () -> Unit,
    onSmsClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onMessengerClick: () -> Unit,
    onTeamsClick: () -> Unit,
    onOtherAppsClick: () -> Unit,
    onEmailClick: () -> Unit,
    onSettingsClick: () -> Unit,
    messageFontSizeSp: Float = 17f,
    messageFontFamily: FontFamily = FontFamily.Default,
    messageTextColor: Color = Color.White
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RelayIQBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Header(
            onSettingsClick = onSettingsClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MicrophoneButton(
                onClick = onSpeakClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Tap to speak",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        MessageCard(
            message = message,
            onMessageChange = onMessageChange,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp),
            fontSizeSp = messageFontSizeSp,
            fontFamily = messageFontFamily,
            textColor = messageTextColor
        )

        Spacer(modifier = Modifier.height(15.dp))

        PolishButton(
            isPolishing = isPolishing,
            onClick = onPolishClick,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(13.dp))

        ClearButton(
            onClick = onClearClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(17.dp))

        ActionButtons(
            onSmsClick = onSmsClick,
            onWhatsAppClick = onWhatsAppClick,
            onMessengerClick = onMessengerClick,
            onTeamsClick = onTeamsClick,
            onOtherAppsClick = onOtherAppsClick,
            onEmailClick = onEmailClick
        )

        Spacer(modifier = Modifier.height(17.dp))

        Text(
            text = "Sending opens your phone's own Messages, WhatsApp, or other apps " +
                "with the text pre-filled so you pick the contact yourself - nothing " +
                "sends automatically.",
            color = Color.White.copy(alpha = 0.45f),
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))
    }
}
