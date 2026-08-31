package com.k.hosken.relayiq.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun MicIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val centerX = w / 2f

        val bodyWidth = w * 0.34f
        val bodyTop = h * 0.06f
        val bodyHeight = h * 0.5f

        drawRoundRect(
            color = tint,
            topLeft = Offset(centerX - bodyWidth / 2f, bodyTop),
            size = Size(bodyWidth, bodyHeight),
            cornerRadius = CornerRadius(bodyWidth / 2f, bodyWidth / 2f)
        )

        val strokeWidth = w * 0.08f

        val cradleWidth = w * 0.58f
        val cradleHeight = h * 0.34f
        val cradleTop = bodyTop + bodyHeight * 0.6f

        drawArc(
            color = tint,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(centerX - cradleWidth / 2f, cradleTop),
            size = Size(cradleWidth, cradleHeight),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        val stemTop = cradleTop + cradleHeight / 2f
        val stemBottom = h * 0.9f

        drawLine(
            color = tint,
            start = Offset(centerX, stemTop),
            end = Offset(centerX, stemBottom),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        val baseWidth = w * 0.32f

        drawLine(
            color = tint,
            start = Offset(centerX - baseWidth / 2f, stemBottom),
            end = Offset(centerX + baseWidth / 2f, stemBottom),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}
