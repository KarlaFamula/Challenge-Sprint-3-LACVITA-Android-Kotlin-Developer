package karlafamula.com.github.lacvita.ui.screens.perfil

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.ui.components.LacVitaBottomBar
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar

private data class ItemMenuPerfil(
    val titulo: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

/**
 * Aba Perfil: identificação da nutriz e acesso rápido aos dados pessoais,
 * histórico de doações, endereços salvos, configurações e suporte.
 */
@Composable
fun PerfilNutrizScreen(
    currentRoute: String?,
    onNavigateTab: (String) -> Unit,
    onHistoricoClick: () -> Unit,
    onFaqClick: () -> Unit,
    onSair: () -> Unit
) {
    val nutriz = MockDataProvider.nutrizLogada

    val itens = listOf(
        ItemMenuPerfil("Meus dados", Icons.Filled.Person) { },
        ItemMenuPerfil("Histórico de doações", Icons.Filled.History, onHistoricoClick),
        ItemMenuPerfil("Endereços salvos", Icons.Filled.Place) { },
        ItemMenuPerfil("Configurações", Icons.Filled.Settings) { },
        ItemMenuPerfil("Ajuda e suporte", Icons.Filled.HelpOutline, onFaqClick)
    )

    Scaffold(
        topBar = { LacVitaTopBar(title = "Meu perfil") },
        bottomBar = { LacVitaBottomBar(currentRoute = currentRoute, onNavigate = onNavigateTab) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.padding(bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = nutriz.nome.first().toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.padding(start = 14.dp)) {
                        Text(text = nutriz.nome, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(
                            text = nutriz.tipoDoadora,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                        )
                    }
                }
            }

            items(itens.size) { index ->
                val item = itens[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable(onClick = item.onClick),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = item.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Text(text = item.titulo, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 14.dp))
                        }
                        Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .clickable(onClick = onSair),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                        Text(
                            text = "Sair",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 14.dp)
                        )
                    }
                }
            }
        }
    }
}
