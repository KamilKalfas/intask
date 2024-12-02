package pl.inpost.recruitmenttask.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun InPostRecruitmentTaskTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = InPostRecruitmentTaskTypography,
        content = content
    )
}