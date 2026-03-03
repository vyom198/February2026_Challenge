package com.vs.february2026_challenge.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = text_primary,
    surface = surface,
    onSurface = text_primary,
    background = background
)

@Composable
fun February2026_ChallengeTheme(

    content: @Composable () -> Unit
) {


    MaterialTheme(
        colorScheme =LightColorScheme,
        typography = Typography,
        content = content
    )
}