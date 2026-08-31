package com.k.hosken.relayiq.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ClearButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Clear",
        color = Color.White.copy(alpha = 0.6f),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier.clickable {
            onClick()
        }
    )
}
