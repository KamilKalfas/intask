package pl.inpost.recruitmenttask.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val purple_700 = Color(0xFF3700B3)
val teal_200 = Color(0xFF03DAC5)
val teal_700 = Color(0xFF018786)
val surface = Color(0xFFF2F2F2)
val onPrimary = Color(0xFF404041)
val grayishDividerText = Color(0xFFBBBDBF)

val LightThemeColors = lightColors(
    primary = Color.White,
    primaryVariant = purple_700,
    onPrimary = onPrimary,
    secondary = teal_200,
    secondaryVariant = teal_700,
    onSecondary = Color.Black,
    surface = surface,
    background = Color.White,
    error = Color.Red
)

val Colors.brandingYellow: Color
    get() = Color(0xFFFFCD00)
val Colors.dividerText: Color
    get() = grayishDividerText
