package com.sobolev.calculatorapp_info_tecs_25.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = Black,
    onBackground = White,
    primary = Grey500,
    secondary = Green200,
    tertiary = Blue200,
    primaryContainer = Black,
    onPrimaryContainer = White,
    onSecondaryContainer = Grey100
)

private val LightColorScheme = lightColorScheme(
    background = White,
    onBackground = Black,
    primary = Grey100,
    secondary = Green100,
    tertiary = Blue100,
    primaryContainer = White,
    onPrimaryContainer = Black,
    onSecondaryContainer = Black
)


@Composable
fun CalculatorApp_info_tecs_25Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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