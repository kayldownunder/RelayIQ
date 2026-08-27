package com.k.hosken.relay.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k.hosken.relay.ui.theme.ColorOption
import com.k.hosken.relay.ui.theme.FontOption
import com.k.hosken.relay.ui.theme.MAX_TEXT_SIZE_SP
import com.k.hosken.relay.ui.theme.MIN_TEXT_SIZE_SP
import com.k.hosken.relay.ui.theme.RelayBackground
import com.k.hosken.relay.ui.theme.RelayOrange
import com.k.hosken.relay.ui.theme.colorOptions
import com.k.hosken.relay.ui.theme.fontOptions
import kotlin.math.roundToInt

@Composable
fun SettingsScreen(
    textSizeSp: Float,
    onTextSizeChange: (Float) -> Unit,
    claudeApiKey: String,
    onClaudeApiKeyChange: (String) -> Unit,
    selectedFontName: String,
    onFontChange: (String) -> Unit,
    selectedColorName: String,
    onColorChange: (String) -> Unit,
    onBackClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RelayBackground)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.08f))
                        .clickable {
                            onBackClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "←",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Settings",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            val selectedFontFamily =
                fontOptions.firstOrNull { it.name == selectedFontName }?.family
                    ?: FontFamily.Default

            val selectedTextColor =
                colorOptions.firstOrNull { it.name == selectedColorName }?.color
                    ?: Color.White

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White.copy(alpha = 0.08f))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sample message",
                    color = selectedTextColor,
                    fontFamily = selectedFontFamily,
                    fontSize = textSizeSp.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextSizeSetting(
                textSizeSp = textSizeSp,
                onTextSizeChange = onTextSizeChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            FontStyleSetting(
                selectedFontName = selectedFontName,
                onFontChange = onFontChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextColourSetting(
                selectedColorName = selectedColorName,
                onColorChange = onColorChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            ClaudeApiKeySetting(
                apiKey = claudeApiKey,
                onApiKeyChange = onClaudeApiKeyChange
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ClaudeApiKeySetting(
    apiKey: String,
    onApiKeyChange: (String) -> Unit
) {
    var isFieldVisible by remember { mutableStateOf(false) }
    var isTextVisible by remember { mutableStateOf(false) }

    SettingsSectionContainer {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Claude API Key",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = if (isFieldVisible) "🔓" else "🔒",
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    isFieldVisible = !isFieldVisible
                    isTextVisible = false
                }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Used by \"Fix spelling & punctuation\" to refine your message with Claude AI. Tap the lock to view or edit it.",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 13.sp
        )

        if (isFieldVisible) {

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = apiKey,
                onValueChange = onApiKeyChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text(text = "sk-ant-...", color = Color.White.copy(alpha = 0.5f))
                },
                visualTransformation =
                    if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        text = if (isTextVisible) "👁" else "🙈",
                        fontSize = 18.sp,
                        modifier = Modifier.clickable { isTextVisible = !isTextVisible }
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = RelayOrange,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    cursorColor = Color.White
                )
            )
        }
    }
}

@Composable
private fun SettingsSectionContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .padding(16.dp),
        content = content
    )
}

@Composable
private fun SettingsHeaderRow(
    label: String,
    valueDisplay: @Composable () -> Unit,
    expanded: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            valueDisplay()

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (expanded) "▲" else "▼",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun TextSizeSetting(
    textSizeSp: Float,
    onTextSizeChange: (Float) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    SettingsSectionContainer {

        SettingsHeaderRow(
            label = "Text Size",
            valueDisplay = {
                Text(
                    text = "${textSizeSp.roundToInt()}",
                    color = Color.White,
                    fontSize = 16.sp
                )
            },
            expanded = expanded,
            onToggle = { expanded = !expanded }
        )

        if (expanded) {

            Spacer(modifier = Modifier.height(12.dp))

            Slider(
                value = textSizeSp,
                onValueChange = onTextSizeChange,
                valueRange = MIN_TEXT_SIZE_SP..MAX_TEXT_SIZE_SP,
                colors = SliderDefaults.colors(
                    thumbColor = RelayOrange,
                    activeTrackColor = RelayOrange,
                    inactiveTrackColor = Color.White.copy(alpha = 0.25f)
                )
            )
        }
    }
}

@Composable
private fun FontStyleSetting(
    selectedFontName: String,
    onFontChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    SettingsSectionContainer {

        SettingsHeaderRow(
            label = "Font Style",
            valueDisplay = {
                Text(
                    text = selectedFontName,
                    color = Color.White,
                    fontSize = 16.sp
                )
            },
            expanded = expanded,
            onToggle = { expanded = !expanded }
        )

        if (expanded) {

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.heightIn(max = 220.dp)
            ) {
                items(fontOptions) { option: FontOption ->

                    val isSelected = option.name == selectedFontName

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (isSelected)
                                    RelayOrange.copy(alpha = 0.25f)
                                else
                                    Color.Transparent
                            )
                            .clickable {
                                onFontChange(option.name)
                                expanded = false
                            }
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = option.name,
                            color = Color.White,
                            fontFamily = option.family,
                            fontSize = 17.sp,
                            modifier = Modifier.weight(1f)
                        )

                        if (isSelected) {
                            Text(
                                text = "✓",
                                color = Color.White,
                                fontSize = 17.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TextColourSetting(
    selectedColorName: String,
    onColorChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedColor =
        colorOptions.firstOrNull { it.name == selectedColorName }?.color
            ?: Color.White

    SettingsSectionContainer {

        SettingsHeaderRow(
            label = "Text Colour",
            valueDisplay = {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = selectedColorName,
                    color = Color.White,
                    fontSize = 16.sp
                )
            },
            expanded = expanded,
            onToggle = { expanded = !expanded }
        )

        if (expanded) {

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.heightIn(max = 260.dp)
            ) {
                items(colorOptions) { option: ColorOption ->

                    val isSelected = option.name == selectedColorName

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (isSelected)
                                    RelayOrange.copy(alpha = 0.25f)
                                else
                                    Color.Transparent
                            )
                            .clickable {
                                onColorChange(option.name)
                                expanded = false
                            }
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(option.color)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = option.name,
                            color = Color.White,
                            fontSize = 17.sp,
                            modifier = Modifier.weight(1f)
                        )

                        if (isSelected) {
                            Text(
                                text = "✓",
                                color = Color.White,
                                fontSize = 17.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
