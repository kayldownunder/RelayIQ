package com.k.hosken.relay.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.k.hosken.relay.ui.theme.RelayOrange

@Composable
fun MicrophoneButton(
    onClick: () -> Unit
) {

    val transition =
        rememberInfiniteTransition(
            label = "micPulse"
        )

    val scale =
        transition.animateFloat(
            initialValue = 0.98f,
            targetValue = 1.02f,
            animationSpec =
                infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                ),
            label = "mic"
        )

    Box(
        modifier = Modifier
            .size(190.dp)
            .scale(scale.value)
            .clip(CircleShape)
            .background(RelayOrange)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        MicIcon(
            tint = Color.White,
            modifier = Modifier.size(78.dp)
        )
    }
}
