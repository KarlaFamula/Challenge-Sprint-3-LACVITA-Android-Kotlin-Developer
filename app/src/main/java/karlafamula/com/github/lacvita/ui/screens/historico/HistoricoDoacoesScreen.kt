package karlafamula.com.github.lacvita.ui.screens.historico

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import karlafamula.com.github.lacvita.ui.theme.LacVitaSuccess

private val diasSemana = listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb")

// Junho de 2026 começa numa segunda-feira (índice 1 na semana Dom-Sáb) e tem 30 dias.
private const val DIAS_NO_MES = 30
private const val OFFSET_PRIMEIRO_DIA = 1

/**
 * Calendário do mês com os dias em que houve doação destacados, além de um
 * resumo consolidado (litros doados, número de doações e recém-nascidos
 * alimentados no período).
 */
@Composable
fun HistoricoDoacoesScreen(onBackClick: () -> Unit) {
    val diasComDoacao = MockDataProvider.diasDoacaoJunho2026.associateBy { it.dia }
    val resumo = MockDataProvider.resumoJunho2026

    Scaffold(topBar = { LacVitaTopBar(title = "Histórico de Doações", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Text(
                text = "Acompanhe os dias em que você doou.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = resumo.mesReferencia,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                diasSemana.forEach { dia ->
                    Text(
                        text = dia,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp)
            ) {
                items(OFFSET_PRIMEIRO_DIA) {
                    androidx.compose.foundation.layout.Box(modifier = Modifier.aspectRatio(1f))
                }
                items(DIAS_NO_MES) { index ->
                    val dia = index + 1
                    val doacao = diasComDoacao[dia]
                    DiaCalendario(dia = dia, doou = doacao != null && doacao.litros > 0, agendado = doacao != null && doacao.litros == 0.0)
                }
            }

            Text(
                text = "Resumo do mês",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ResumoValor(valor = "${resumo.totalLitros} L", legenda = "Leite doado")
                    ResumoValor(valor = "${resumo.totalDoacoes}", legenda = "Doações")
                    ResumoValor(valor = "${resumo.recemNascidosAlimentados}", legenda = "RN alimentados")
                }
            }

            Text(
                text = "Os dados representados são baseados nas doações registradas no app.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Composable
private fun DiaCalendario(dia: Int, doou: Boolean, agendado: Boolean) {
    val corFundo = when {
        doou -> LacVitaSuccess
        agendado -> MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
        else -> Color.Transparent
    }
    val corTexto = if (doou) Color.White else MaterialTheme.colorScheme.onSurface

    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(3.dp)
            .background(corFundo, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$dia", style = MaterialTheme.typography.bodySmall, color = corTexto)
    }
}

@Composable
private fun ResumoValor(valor: String, legenda: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = valor, color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(text = legenda, color = Color.White.copy(alpha = 0.85f), style = MaterialTheme.typography.labelSmall)
    }
}
