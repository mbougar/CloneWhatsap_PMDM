package com.mbougar.clonewhatsap_pmdm.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF128C7E),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF262D31),
    onPrimaryContainer = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF128C7E),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF262D31),
    onPrimaryContainer = Color.White,
    background = Color(0xFFFFFFFF),
    onBackground = Color.Black
)

@Composable
fun WhatsAppCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
