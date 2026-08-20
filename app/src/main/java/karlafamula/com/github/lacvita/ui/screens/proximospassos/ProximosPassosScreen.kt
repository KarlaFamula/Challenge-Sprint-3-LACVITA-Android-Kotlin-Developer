package karlafamula.com.github.lacvita.ui.screens.proximospassos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import karlafamula.com.github.lacvita.ui.theme.LacVitaSuccess

private val passos = listOf(
    "Lave bem as mãos",
    "Higienize os recipientes",
    "Armazene corretamente",
    "Aguarde a coleta"
)

/**
 * Tela exibida logo após a triagem ser concluída com sucesso: confirma que a
 * nutriz pode doar e apresenta o passo a passo rápido de preparo do leite.
 */
@Composable
fun ProximosPassosScreen(
    onContinuar: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(topBar = { LacVitaTopBar(title = "Próximos passos", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(LacVitaSuccess.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = LacVitaSuccess,
                    modifier = Modifier.size(36.dp)
                )
            }

            Text(
                text = "Tudo certo!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Você pode doar.",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Siga os passos abaixo para preparar seu leite para a coleta.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 8.dp, bottom = 28.dp)
            )

            passos.forEachIndexed { index, passo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = passo,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 14.dp)
                    )
                }
            }

            Button(
                onClick = onContinuar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Continuar")
            }
        }
    }
}
