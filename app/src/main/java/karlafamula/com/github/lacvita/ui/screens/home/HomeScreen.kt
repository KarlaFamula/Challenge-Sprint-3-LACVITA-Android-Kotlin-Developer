package karlafamula.com.github.lacvita.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import karlafamula.com.github.lacvita.model.StatusColeta
import karlafamula.com.github.lacvita.ui.components.LacVitaBottomBar
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar

/**
 * Tela inicial (aba Início): resume a próxima coleta, o impacto da nutriz,
 * uma dica diária, pendências de exames na Central da Saúde e o acesso rápido
 * às dúvidas frequentes — reunindo os principais atalhos do app.
 */
@Composable
fun HomeScreen(
    currentRoute: String?,
    onNavigateTab: (String) -> Unit,
    onEncontrarPonto: () -> Unit,
    onCentralSaudeClick: () -> Unit,
    onFaqClick: () -> Unit,
    onNotificacoesClick: () -> Unit
) {
    val nutriz = MockDataProvider.nutrizLogada
    val proximaColeta = MockDataProvider.historicoColetas.firstOrNull { it.status == StatusColeta.AGENDADA }
    val primeiroNome = nutriz.nome.substringBefore(" ")

    Scaffold(
        topBar = {
            LacVitaTopBar(
                title = "LacVita",
                action = {
                    IconButton(onClick = onNotificacoesClick) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notificações",
                            tint = Color.White
                        )
                    }
                }
            )
        },
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
                    text = "Olá, $primeiroNome! \uD83D\uDC4B",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Que bom ter você aqui!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            item {
                if (proximaColeta != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = "Próxima coleta",
                                color = Color.White.copy(alpha = 0.85f),
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text(
                                text = "${proximaColeta.data}  •  ${proximaColeta.horario}",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = proximaColeta.blhNome,
                                color = Color.White.copy(alpha = 0.9f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Button(
                                onClick = onEncontrarPonto,
                                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.padding(top = 14.dp)
                            ) {
                                Icon(imageVector = Icons.Filled.Place, contentDescription = null, modifier = Modifier.size(18.dp))
                                Text(text = "  Encontrar ponto de coleta")
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ImpactoMiniCard(
                        valor = "${nutriz.totalLitrosDoados} L",
                        legenda = "Litros doados",
                        icon = Icons.Filled.LocalDrink,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateTab("impacto") }
                    )
                    ImpactoMiniCard(
                        valor = "${nutriz.bebesImpactados}",
                        legenda = "Bebês alimentados",
                        icon = Icons.Filled.Favorite,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateTab("impacto") }
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Dica diária para você",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = MockDataProvider.dicaDoDia,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable(onClick = onCentralSaudeClick),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Vaccines, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Column(modifier = Modifier.padding(start = 12.dp)) {
                            Text(text = "Central da saúde", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text(
                                text = "Você tem ${MockDataProvider.examesPendentes.size} testes pendentes: HIV, Hepatites B e C",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onFaqClick),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.HelpOutline, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Column(modifier = Modifier.padding(start = 12.dp)) {
                            Text(text = "FAQ", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text(
                                text = "Tire suas dúvidas sobre doação de leite humano",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ImpactoMiniCard(
    valor: String,
    legenda: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Text(
                text = valor,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = legenda,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}
