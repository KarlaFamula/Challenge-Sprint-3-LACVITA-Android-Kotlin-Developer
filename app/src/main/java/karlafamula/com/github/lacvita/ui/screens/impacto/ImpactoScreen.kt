package karlafamula.com.github.lacvita.ui.screens.impacto

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.ui.components.LacVitaBottomBar
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar

private val periodos = listOf("Semana", "Mês", "Ano")

/**
 * Aba Impacto: estatísticas pessoais de doação (litros e bebês alimentados),
 * um gráfico simplificado de evolução por período e o volume coletado por
 * polo/BLH na rede, além do atalho para o histórico detalhado de doações.
 */
@Composable
fun ImpactoScreen(
    currentRoute: String?,
    onNavigateTab: (String) -> Unit,
    onHistoricoClick: () -> Unit
) {
    val nutriz = MockDataProvider.nutrizLogada
    var periodoSelecionado by remember { mutableStateOf("Mês") }
    val polos = MockDataProvider.polosComunidade.sortedByDescending { it.litrosNaSemana }
    val maiorValor = polos.maxOf { it.litrosNaSemana }

    Scaffold(
        topBar = { LacVitaTopBar(title = "Meu impacto") },
        bottomBar = { LacVitaBottomBar(currentRoute = currentRoute, onNavigate = onNavigateTab) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Text(
                    text = "Você já doou ${nutriz.totalLitrosDoados} litros",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Isso equivale a ${nutriz.bebesImpactados} bebês alimentados",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
                )
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onHistoricoClick)
                        .padding(bottom = 20.dp),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "Histórico de doações", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text(
                                text = "Veja seu calendário de coletas",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                            )
                        }
                        Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null)
                    }
                }
            }

            item {
                Text(
                    text = "Evolução",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "Selecione um período para verificar sua evolução",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(bottom = 20.dp)) {
                    periodos.forEach { periodo ->
                        FilterChip(
                            selected = periodoSelecionado == periodo,
                            onClick = { periodoSelecionado = periodo },
                            label = { Text(periodo) }
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Litros coletados por polo",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
            }

            items(polos) { polo ->
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = polo.nomePolo, style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${polo.litrosNaSemana} L", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                    }
                    val proporcao = (polo.litrosNaSemana / maiorValor).toFloat()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .padding(top = 6.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(proporcao)
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(6.dp))
                        ) {}
                    }
                }
            }
        }
    }
}
