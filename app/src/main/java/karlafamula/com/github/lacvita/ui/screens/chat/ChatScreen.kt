package karlafamula.com.github.lacvita.ui.screens.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.model.ChatMessage
import karlafamula.com.github.lacvita.model.Remetente
import karlafamula.com.github.lacvita.ui.components.LacVitaBottomBar
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar

/**
 * Aba Chat: alterna entre o assistente virtual (respostas simuladas a partir
 * de perguntas rápidas) e o encaminhamento para um especialista via WhatsApp.
 */
@Composable
fun ChatScreen(
    currentRoute: String?,
    onNavigateTab: (String) -> Unit
) {
    var abaSelecionada by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { LacVitaTopBar(title = "Chat") },
        bottomBar = { LacVitaBottomBar(currentRoute = currentRoute, onNavigate = onNavigateTab) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TabRow(selectedTabIndex = abaSelecionada) {
                Tab(
                    selected = abaSelecionada == 0,
                    onClick = { abaSelecionada = 0 },
                    text = { Text("Assistente") }
                )
                Tab(
                    selected = abaSelecionada == 1,
                    onClick = { abaSelecionada = 1 },
                    text = { Text("Especialista") }
                )
            }

            if (abaSelecionada == 0) {
                AssistenteVirtual()
            } else {
                EncaminharEspecialista()
            }
        }
    }
}

@Composable
private fun AssistenteVirtual() {
    val mensagens = remember { MockDataProvider.mensagensChatAssistente.toMutableStateList() }
    var textoDigitado by remember { mutableStateOf("") }
    var proximoId by remember { mutableStateOf(mensagens.size + 1) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(mensagens, key = { it.id }) { mensagem ->
                BolhaMensagem(mensagem)
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(MockDataProvider.perguntasRapidas) { rapida ->
                SuggestionChip(
                    onClick = {
                        mensagens.add(ChatMessage(proximoId++, rapida.pergunta, Remetente.USUARIA))
                        mensagens.add(ChatMessage(proximoId++, rapida.resposta, Remetente.ASSISTENTE))
                    },
                    label = { Text(rapida.pergunta) },
                    colors = SuggestionChipDefaults.suggestionChipColors()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textoDigitado,
                onValueChange = { textoDigitado = it },
                placeholder = { Text("Digite sua mensagem...") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                singleLine = true
            )
            IconButton(
                onClick = {
                    if (textoDigitado.isNotBlank()) {
                        mensagens.add(ChatMessage(proximoId++, textoDigitado, Remetente.USUARIA))
                        mensagens.add(
                            ChatMessage(
                                proximoId++,
                                "Obrigada por sua mensagem! Nossa equipe já vai te responder. Enquanto isso, veja as perguntas rápidas acima.",
                                Remetente.ASSISTENTE
                            )
                        )
                        textoDigitado = ""
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "Enviar", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
private fun BolhaMensagem(mensagem: ChatMessage) {
    val isUsuaria = mensagem.remetente == Remetente.USUARIA
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUsuaria) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier.wrapContentWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isUsuaria) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Text(
                text = mensagem.texto,
                color = if (isUsuaria) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
private fun EncaminharEspecialista() {
    var redirecionado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.SupportAgent,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.height(56.dp)
        )
        Text(
            text = "Você será direcionado ao WhatsApp para falar com um de nossos especialistas",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Text(
            text = "Segunda a Sábado, das 8h às 17h\nAtendimento seguro e acolhedor",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        if (redirecionado) {
            Text(
                text = "Simulação: você seria direcionado ao aplicativo do WhatsApp agora.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Button(
            onClick = { redirecionado = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(top = 28.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Conversar no WhatsApp")
        }
        OutlinedButton(
            onClick = { redirecionado = false },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(top = 12.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Agora não")
        }
    }
}
