package com.k.hosken.relay.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.k.hosken.relay.R

@Composable
fun ActionButtons(
    onSmsClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onMessengerClick: () -> Unit,
    onTeamsClick: () -> Unit,
    onOtherAppsClick: () -> Unit,
    onEmailClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(
                    R.drawable.sms_button
                ),
                contentDescription = "SMS",
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .clickable {
                        onSmsClick()
                    }
            )

            Image(
                painter = painterResource(
                    R.drawable.whatsapp_button
                ),
                contentDescription = "WhatsApp",
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .clickable {
                        onWhatsAppClick()
                    }
            )

            Image(
                painter = painterResource(
                    R.drawable.messenger_button
                ),
                contentDescription = "Messenger",
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .clickable {
                        onMessengerClick()
                    }
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(
                    R.drawable.teams
                ),
                contentDescription = "Teams",
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .clickable {
                        onTeamsClick()
                    }
            )

            Image(
                painter = painterResource(
                    R.drawable.email_button
                ),
                contentDescription = "Email",
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .clickable {
                        onEmailClick()
                    }
            )

            Image(
                painter = painterResource(
                    R.drawable.other_apps
                ),
                contentDescription = "Other Apps",
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .clickable {
                        onOtherAppsClick()
                    }
            )
        }
    }
}
