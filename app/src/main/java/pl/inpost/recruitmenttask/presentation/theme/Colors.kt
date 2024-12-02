package pl.inpost.recruitmenttask.presentation.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val purple_200 = Color(0xFFBB86FC)
val purple_500 = Color(0xFF6200EE)
val purple_700 = Color(0xFF3700B3)
val teal_200 = Color(0xFF03DAC5)
val teal_700 = Color(0xFF018786)
val black = Color.Black
val white = Color.White

val LightThemeColors = lightColors(
    primary = purple_500,
    primaryVariant = purple_700,
    onPrimary = white,
    secondary = teal_200,
    secondaryVariant = teal_700,
    onSecondary = black,
    surface = white,
    error = Color.Red
)

val DarkThemeColors = darkColors(
    primary = purple_200,
    primaryVariant = purple_700,
    onPrimary = black,
    secondary = teal_200,
    secondaryVariant = teal_200,
    onSecondary = black,
    background = black,
    error = Color.Red
)