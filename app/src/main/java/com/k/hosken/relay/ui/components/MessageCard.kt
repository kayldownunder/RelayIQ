package com.k.hosken.relay.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageCard(
    message: String,
    onMessageChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontSizeSp: Float = 17f,
    fontFamily: FontFamily = FontFamily.Default,
    textColor: Color = Color.White
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = "YOUR MESSAGE",
            color = Color.White.copy(alpha = 0.55f),
            fontSize = 12.sp,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = message,
            onValueChange = onMessageChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 160.dp),
            placeholder = {
                Text(
                    text = "Your dictated text appears here, or type it directly...",
                    color = Color.White.copy(alpha = 0.35f),
                    fontSize = fontSizeSp.sp,
                    fontFamily = fontFamily
                )
            },
            textStyle = TextStyle(
                color = textColor,
                fontSize = fontSizeSp.sp,
                fontFamily = fontFamily
            ),
            singleLine = false,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                focusedBorderColor = Color.White.copy(alpha = 0.4f),
                unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color.White
            )
        )
    }
}
