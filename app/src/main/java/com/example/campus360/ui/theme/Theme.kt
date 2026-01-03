package com.example.campus360.ui.theme

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
    primary = Primary80,
    onPrimary = Color(0xFF003640),
    primaryContainer = PrimaryContainer80,
    secondary = Secondary80,
    onSecondary = Color(0xFF1F3438),
    secondaryContainer = SecondaryContainer80,
    tertiary = Tertiary80,
    tertiaryContainer = TertiaryContainer80,
    error = Error
)

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer40,
    secondary = Secondary40,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer40,
    tertiary = Tertiary40,
    tertiaryContainer = TertiaryContainer40,
    error = Error
)
@Composable
fun Campus360Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}