package karlafamula.com.github.lacvita.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.ui.theme.LacVitaBlueDark
import karlafamula.com.github.lacvita.ui.theme.LacVitaBluePrimary
import kotlinx.coroutines.delay

/**
 * Tela de abertura do app: apresenta a marca LacVita e o slogan, seguindo para o
 * login automaticamente após um pequeno intervalo (ou ao toque, para garantir fluxo).
 */
@Composable
fun SplashScreen(onContinuar: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1600)
        onContinuar()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(LacVitaBlueDark, LacVitaBluePrimary))
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Logo LacVita",
                    tint = LacVitaBluePrimary,
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = "LacVita",
                color = Color.White,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "Cada gota importa. Cada conexão transforma.",
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, start = 32.dp, end = 32.dp)
            )
        }
    }
}
