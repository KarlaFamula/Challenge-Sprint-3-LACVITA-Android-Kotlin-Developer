package karlafamula.com.github.lacvita.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import karlafamula.com.github.lacvita.navigation.LacVitaDestinations

/**
 * Representa cada uma das 5 abas principais do app (Início, Coletas, Chat,
 * Impacto e Perfil), espelhando a navegação inferior do protótipo.
 */
private data class BottomItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

private val bottomItems = listOf(
    BottomItem(LacVitaDestinations.HOME, "Início", Icons.Filled.Home),
    BottomItem(LacVitaDestinations.COLETAS, "Coletas", Icons.Filled.CalendarMonth),
    BottomItem(LacVitaDestinations.CHAT, "Chat", Icons.Filled.ChatBubbleOutline),
    BottomItem(LacVitaDestinations.IMPACTO, "Impacto", Icons.Filled.Favorite),
    BottomItem(LacVitaDestinations.PERFIL, "Perfil", Icons.Filled.Person)
)

/**
 * Barra de navegação inferior fixa nas 5 telas principais do app.
 * Cada item navega para a rota raiz da aba, preservando o estado das demais
 * (padrão recomendado do Navigation Compose para navegação por abas).
 */
@Composable
fun LacVitaBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        bottomItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) }
            )
        }
    }
}
