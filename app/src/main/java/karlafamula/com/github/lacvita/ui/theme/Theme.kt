package karlafamula.com.github.lacvita.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = LacVitaBlueLight,
    secondary = LacVitaAccent,
    tertiary = LacVitaBlueSky,
    background = Color0F1B2E,
    surface = Color15243D,
    error = LacVitaError
)

private val LightColorScheme = lightColorScheme(
    primary = LacVitaBluePrimary,
    onPrimary = LacVitaSurface,
    primaryContainer = LacVitaBlueSky,
    secondary = LacVitaAccent,
    tertiary = LacVitaBlueDark,
    background = LacVitaBackground,
    surface = LacVitaSurface,
    onBackground = LacVitaTextPrimary,
    onSurface = LacVitaTextPrimary,
    error = LacVitaError
)

@Composable
fun LacVitaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Cores fixas da marca (dynamic color desligado para preservar a identidade visual)
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = androidx.compose.ui.platform.LocalContext.current
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
