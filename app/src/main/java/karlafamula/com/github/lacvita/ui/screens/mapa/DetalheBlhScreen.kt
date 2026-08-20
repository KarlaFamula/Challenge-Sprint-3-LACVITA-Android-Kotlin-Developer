package karlafamula.com.github.lacvita.ui.screens.mapa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import kotlinx.coroutines.launch

/**
 * Mostra as informações completas de um BLH específico, recebido por parâmetro de navegação
 * (id) vindo da tela de mapa. Permite simular o agendamento de uma coleta naquele posto.
 */
@Composable
fun DetalheBlhScreen(
    blhId: Int,
    onBackClick: () -> Unit
) {
    val blh = MockDataProvider.blhPorId(blhId)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { LacVitaTopBar(title = "Detalhes do BLH", onBackClick = onBackClick) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        if (blh == null) {
            Text(
                text = "BLH não encontrado.",
                modifier = Modifier.padding(innerPadding).padding(16.dp)
            )
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Text(
                text = blh.nome,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${blh.cidade} - ${blh.estado}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )

            InfoLinha(label = "Endereço", valor = blh.endereco)
            InfoLinha(label = "Horário de funcionamento", valor = blh.horarioFuncionamento)
            InfoLinha(label = "Telefone", valor = blh.telefone)
            InfoLinha(label = "Distância", valor = "${blh.distanciaKm} km")
            InfoLinha(
                label = "Vagas hoje",
                valor = if (blh.vagasHoje > 0) "${blh.vagasHoje} vagas disponíveis" else "Sem vagas disponíveis hoje"
            )

            Text(
                text = "Serviços oferecidos",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ServicoChip(texto = if (blh.aceitaColetaDomiciliar) "Coleta em casa" else "Coleta em casa indisponível")
                ServicoChip(texto = "Ponto de entrega")
            }

            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Coleta agendada com sucesso em ${blh.nome}!")
                    }
                },
                enabled = blh.vagasHoje > 0,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Agendar coleta")
            }
        }
    }
}

@Composable
private fun ServicoChip(texto: String) {
    Card(
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Text(
            text = texto,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun InfoLinha(label: String, valor: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = valor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
