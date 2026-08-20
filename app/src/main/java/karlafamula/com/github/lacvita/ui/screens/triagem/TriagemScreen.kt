package karlafamula.com.github.lacvita.ui.screens.triagem

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
 * Questionário de triagem de saúde (1/5 do cadastro), verificando se a nutriz
 * está apta a doar antes de seguir para o guia de próximos passos.
 */
@Composable
fun TriagemScreen(
    onContinuar: () -> Unit,
    onBackClick: () -> Unit
) {
    var saudavel by remember { mutableStateOf<Boolean?>(null) }
    var medicamento by remember { mutableStateOf<Boolean?>(null) }
    var doencaRecente by remember { mutableStateOf<Boolean?>(null) }

    val podeContinuar = saudavel != null && medicamento != null && doencaRecente != null

    Scaffold(topBar = { LacVitaTopBar(title = "Triagem", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Triagem  •  1/5",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Responda com sinceridade para sabermos se você está apta a doar hoje.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            SimNaoSelector(
                pergunta = "Você está saudável?",
                valor = saudavel,
                onValorChange = { saudavel = it }
            )
            SimNaoSelector(
                pergunta = "Está tomando algum medicamento?",
                valor = medicamento,
                onValorChange = { medicamento = it },
                modifier = Modifier.padding(top = 20.dp)
            )
            SimNaoSelector(
                pergunta = "Teve alguma doença recentemente?",
                valor = doencaRecente,
                onValorChange = { doencaRecente = it },
                modifier = Modifier.padding(top = 20.dp)
            )

            Button(
                onClick = onContinuar,
                enabled = podeContinuar,
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
