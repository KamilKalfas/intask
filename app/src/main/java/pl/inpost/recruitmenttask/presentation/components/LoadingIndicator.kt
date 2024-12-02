package pl.inpost.recruitmenttask.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import pl.inpost.recruitmenttask.presentation.theme.brandingYellow

@Composable
fun LoadingIndicator(
    modifier: Modifier,
    color: Color = MaterialTheme.colors.brandingYellow,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth
) = Box(
    modifier.fillMaxSize()
) {
    CircularProgressIndicator(
        modifier = modifier.align(Alignment.Center),
        color = color,
        strokeWidth = strokeWidth
    )
}