package karlafamula.com.github.lacvita.ui.screens.coletas

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.ui.components.LacVitaBottomBar
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import kotlinx.coroutines.launch

private enum class TipoColeta { EM_CASA, LEVAR_A_PONTO }

/**
 * Aba Coletas: formulário de agendamento (tipo de coleta, data e horário).
 * Se a nutriz escolher "levar a um ponto", oferece atalho para a lista de
 * pontos de coleta; caso contrário, confirma o agendamento em casa.
 */
@Composable
fun AgendamentoScreen(
    currentRoute: String?,
    onNavigateTab: (String) -> Unit,
    onVerPontosDeColeta: () -> Unit
) {
    var tipoColeta by remember { mutableStateOf(TipoColeta.EM_CASA) }
    var data by remember { mutableStateOf("21/06/2026") }
    var hora by remember { mutableStateOf("14:00") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { LacVitaTopBar(title = "Agendamento") },
        bottomBar = { LacVitaBottomBar(currentRoute = currentRoute, onNavigate = onNavigateTab) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Text(
                    text = "Escolha a melhor opção para você",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Text(text = "Tipo de coleta", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)

                OpcaoTipoColeta(
                    titulo = "Coleta em casa",
                    subtitulo = "Um colaborador irá até você",
                    icon = Icons.Filled.HomeWork,
                    selecionado = tipoColeta == TipoColeta.EM_CASA,
                    onClick = { tipoColeta = TipoColeta.EM_CASA }
                )
                OpcaoTipoColeta(
                    titulo = "Levar a um ponto",
                    subtitulo = "Você leva até um ponto próximo",
                    icon = Icons.Filled.Place,
                    selecionado = tipoColeta == TipoColeta.LEVAR_A_PONTO,
                    onClick = { tipoColeta = TipoColeta.LEVAR_A_PONTO }
                )

                Text(
                    text = "Data",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
                )
                CampoSimulado(icon = Icons.Filled.CalendarMonth, valor = data)

                Text(
                    text = "Hora",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                CampoSimulado(icon = Icons.Filled.Schedule, valor = hora)

                if (tipoColeta == TipoColeta.LEVAR_A_PONTO) {
                    Button(
                        onClick = onVerPontosDeColeta,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(top = 28.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Ver pontos de coleta")
                    }
                } else {
                    Button(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Coleta em casa agendada para $data às $hora!")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(top = 28.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Confirmar agendamento")
                    }
                }

                Text(
                    text = "Minhas coletas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp, bottom = 12.dp)
                )
            }

            items(karlafamula.com.github.lacvita.mock.MockDataProvider.historicoColetas) { coleta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "${coleta.data} às ${coleta.horario}", style = MaterialTheme.typography.titleSmall)
                            Text(
                                text = coleta.blhNome,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                            )
                            if (coleta.litros != null) {
                                Text(text = "${coleta.litros} L coletados", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                        karlafamula.com.github.lacvita.ui.components.StatusChip(status = coleta.status)
                    }
                }
            }
        }
    }
}

@Composable
private fun OpcaoTipoColeta(
    titulo: String,
    subtitulo: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selecionado: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selecionado) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (selecionado) 0.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                Text(text = titulo, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(
                    text = subtitulo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                )
            }
            Icon(
                imageVector = if (selecionado) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (selecionado) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
private fun CampoSimulado(icon: androidx.compose.ui.graphics.vector.ImageVector, valor: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
            Text(text = valor, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 12.dp))
        }
    }
}
