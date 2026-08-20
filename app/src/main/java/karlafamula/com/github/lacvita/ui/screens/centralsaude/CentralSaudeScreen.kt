package karlafamula.com.github.lacvita.ui.screens.centralsaude

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.mock.MockDataProvider
import karlafamula.com.github.lacvita.model.StatusExame
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import karlafamula.com.github.lacvita.ui.theme.LacVitaSuccess

/**
 * Central da Saúde: acompanha os exames sorológicos pendentes e simula o
 * upload dos arquivos (PDF, JPG ou PNG) exigidos antes da doação.
 */
@Composable
fun CentralSaudeScreen(onBackClick: () -> Unit) {
    var arquivosEnviados by remember { mutableStateOf(0) }
    val exames = MockDataProvider.examesPendentes

    Scaffold(topBar = { LacVitaTopBar(title = "Central da saúde", onBackClick = onBackClick) }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Text(
                    text = "Testes pendentes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            items(count = exames.size) { index ->
                val exame = exames[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Vaccines, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                            Text(text = exame.nome, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text(
                                text = exame.descricao,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Faça upload aqui!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                )
                Text(
                    text = "Permitido PDF, JPG e PNG",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CloudUpload,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.height(40.dp)
                        )
                        Text(
                            text = "Arraste e solte seu(s) arquivo(s) aqui ou procure em seu dispositivo",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                        )
                        OutlinedButton(onClick = { if (arquivosEnviados < exames.size) arquivosEnviados++ }) {
                            Text(text = "Selecionar arquivo")
                        }
                    }
                }

                if (arquivosEnviados > 0) {
                    Column(modifier = Modifier.padding(top = 16.dp)) {
                        repeat(arquivosEnviados) { index ->
                            ArquivoEnviadoLinha(nome = "resultado_${exames[index].nome.lowercase().replace(" ", "_")}.pdf")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ArquivoEnviadoLinha(nome: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.Description, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            Text(text = nome, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(start = 8.dp))
        }
        Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Enviado", tint = LacVitaSuccess)
    }
}
