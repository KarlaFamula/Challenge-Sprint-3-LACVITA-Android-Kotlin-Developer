package karlafamula.com.github.lacvita.ui.screens.cadastro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import karlafamula.com.github.lacvita.ui.components.SimNaoSelector

/**
 * Primeira etapa do cadastro de uma nova doadora: confirma se é mãe e se
 * está amamentando, antes de seguir para a triagem de saúde.
 */
@Composable
fun DadosPessoaisScreen(
    onContinuar: () -> Unit,
    onBackClick: () -> Unit
) {
    var eMae by remember { mutableStateOf<Boolean?>(null) }
    var amamentando by remember { mutableStateOf<Boolean?>(null) }

    Scaffold(topBar = { LacVitaTopBar(title = "Cadastro", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Para começar, precisamos saber",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Isso nos ajuda a personalizar sua jornada de doação.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp, bottom = 28.dp)
            )

            SimNaoSelector(
                pergunta = "Você é mãe?",
                valor = eMae,
                onValorChange = { eMae = it }
            )
            SimNaoSelector(
                pergunta = "Está amamentando?",
                valor = amamentando,
                onValorChange = { amamentando = it },
                modifier = Modifier.padding(top = 20.dp)
            )

            Button(
                onClick = onContinuar,
                enabled = eMae != null && amamentando != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(top = 36.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Continuar")
            }
        }
    }
}
