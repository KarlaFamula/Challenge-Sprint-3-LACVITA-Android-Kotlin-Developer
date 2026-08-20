package karlafamula.com.github.lacvita.ui.screens.mapa

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import karlafamula.com.github.lacvita.ui.theme.LacVitaSuccess
import karlafamula.com.github.lacvita.ui.theme.LacVitaWarning

/**
 * Lista os bancos de leite humano (BLHs) mais próximos, ordenados por distância mockada.
 * Ao tocar em um item, o app navega para a tela de detalhe daquele BLH específico.
 */
@Composable
fun MapaScreen(
    onBackClick: () -> Unit,
    onBlhClick: (Int) -> Unit
) {
    val blhs = MockDataProvider.blhs.sortedBy { it.distanciaKm }

    Scaffold(topBar = { LacVitaTopBar(title = "Pontos de coleta", onBackClick = onBackClick) }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(blhs) { blh ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onBlhClick(blh.id) },
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = blh.nome,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Text(
                            text = "${blh.cidade} - ${blh.estado} • ${blh.distanciaKm} km",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 6.dp, start = 32.dp)
                        )
                        Text(
                            text = blh.horarioFuncionamento,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            modifier = Modifier.padding(top = 2.dp, start = 32.dp)
                        )
                        Text(
                            text = if (blh.vagasHoje > 0) "${blh.vagasHoje} vagas hoje" else "Sem vagas hoje",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (blh.vagasHoje > 0) LacVitaSuccess else LacVitaWarning,
                            modifier = Modifier.padding(top = 6.dp, start = 32.dp)
                        )
                    }
                }
            }
        }
    }
}
