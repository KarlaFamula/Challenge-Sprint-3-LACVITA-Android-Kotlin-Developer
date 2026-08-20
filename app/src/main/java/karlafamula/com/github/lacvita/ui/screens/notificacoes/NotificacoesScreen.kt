package karlafamula.com.github.lacvita.ui.screens.notificacoes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.model.Notificacao
import karlafamula.com.github.lacvita.model.TipoNotificacao
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar

/**
 * Central de notificações: lembretes de coleta, dicas diárias, conquistas de
 * doação, novidades da rede de pontos de coleta e avisos de acessibilidade.
 */
@Composable
fun NotificacoesScreen(onBackClick: () -> Unit) {
    Scaffold(topBar = { LacVitaTopBar(title = "Notificações", onBackClick = onBackClick) }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(MockDataProvider.notificacoes) { notificacao ->
                NotificacaoCard(notificacao)
            }
        }
    }
}

@Composable
private fun NotificacaoCard(notificacao: Notificacao) {
    val icon = when (notificacao.tipo) {
        TipoNotificacao.LEMBRETE -> Icons.Filled.Notifications
        TipoNotificacao.DICA -> Icons.Filled.Spa
        TipoNotificacao.CONQUISTA -> Icons.Filled.EmojiEvents
        TipoNotificacao.NOVIDADE -> Icons.Filled.Campaign
        TipoNotificacao.ACESSIBILIDADE -> Icons.Filled.Accessibility
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 2.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                Text(text = notificacao.titulo, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(
                    text = notificacao.descricao,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            Text(
                text = notificacao.tempoRelativo,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}
