package com.upb.examen2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EsquemaClaro = lightColorScheme(
    primary = Color(0xFF3B4CCA),
    onPrimary = Color.White,
    secondary = Color(0xFFCC0000),
    tertiary = Color(0xFFFFCB05)
)

private val EsquemaOscuro = darkColorScheme(
    primary = Color(0xFF9DA8FF),
    onPrimary = Color(0xFF0A1160),
    secondary = Color(0xFFFF6B6B),
    tertiary = Color(0xFFFFCB05)
)

@Composable
fun Examen2Theme(
    modoOscuro: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (modoOscuro) EsquemaOscuro else EsquemaClaro,
        content = content
    )
}