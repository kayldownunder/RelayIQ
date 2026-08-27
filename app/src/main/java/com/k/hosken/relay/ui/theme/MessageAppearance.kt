package com.k.hosken.relay.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.k.hosken.relay.R

data class FontOption(
    val name: String,
    val family: FontFamily
)

val fontOptions = listOf(
    FontOption("Arimo (Arial)", FontFamily(Font(R.font.arimo))),
    FontOption("Tinos (Times New Roman)", FontFamily(Font(R.font.tinos))),
    FontOption("Carlito (Calibri)", FontFamily(Font(R.font.carlito))),
    FontOption("Gelasio (Georgia)", FontFamily(Font(R.font.gelasio))),
    FontOption("Cousine (Courier New)", FontFamily(Font(R.font.cousine))),
    FontOption("Roboto", FontFamily(Font(R.font.roboto))),
    FontOption("Open Sans", FontFamily(Font(R.font.open_sans))),
    FontOption("Lato", FontFamily(Font(R.font.lato))),
    FontOption("Montserrat", FontFamily(Font(R.font.montserrat))),
    FontOption("Poppins", FontFamily(Font(R.font.poppins))),
    FontOption("Merriweather", FontFamily(Font(R.font.merriweather))),
    FontOption("Playfair Display", FontFamily(Font(R.font.playfair_display))),
    FontOption("Nunito", FontFamily(Font(R.font.nunito))),
    FontOption("Oswald", FontFamily(Font(R.font.oswald))),
    FontOption("Raleway", FontFamily(Font(R.font.raleway)))
)

data class ColorOption(
    val name: String,
    val color: Color
)

val colorOptions = listOf(
    ColorOption("White", Color.White),
    ColorOption("Black", Color.Black),
    ColorOption("Red", Color(0xFFEF5350)),
    ColorOption("Orange", Color(0xFFFFA726)),
    ColorOption("Yellow", Color(0xFFFFEE58)),
    ColorOption("Green", Color(0xFF66BB6A)),
    ColorOption("Cyan", Color(0xFF26C6DA)),
    ColorOption("Blue", Color(0xFF42A5F5)),
    ColorOption("Purple", Color(0xFFAB47BC)),
    ColorOption("Pink", Color(0xFFEC407A)),
    ColorOption("Gray", Color(0xFFBDBDBD))
)

const val MIN_TEXT_SIZE_SP = 12f
const val MAX_TEXT_SIZE_SP = 32f
