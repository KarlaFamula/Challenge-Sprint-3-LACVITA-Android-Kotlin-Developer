package karlafamula.com.github.lacvita.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.ui.components.LacVitaTopBar
import kotlinx.coroutines.launch

/**
 * Onboarding em 3 passos: apresentação da marca, explicação do funcionamento
 * e a pergunta de entrada que decide o próximo fluxo (login direto para quem
 * já é cadastrada, ou cadastro/triagem para uma nova doadora).
 */
@Composable
fun OnboardingScreen(
    onJaFazParte: () -> Unit,
    onNovaDoadora: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            if (pagerState.currentPage > 0) {
                LacVitaTopBar(
                    title = "LacVita",
                    onBackClick = {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> BemVindoPage()
                    1 -> ComoFuncionaPage()
                    else -> ConectaBlhPage(
                        onJaFazParte = onJaFazParte,
                        onNovaDoadora = onNovaDoadora
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (pagerState.currentPage == index) 10.dp else 8.dp)
                            .background(
                                color = if (pagerState.currentPage == index) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                                },
                                shape = CircleShape
                            )
                    )
                }
            }

            if (pagerState.currentPage < 2) {
                Button(
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = if (pagerState.currentPage == 0) "Começar" else "Próximo")
                }
            }
        }
    }
}

@Composable
private fun BemVindoPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "Logo LacVita",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
        }
        Text(
            text = "Bem-vindo ao LacVita!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(
            text = "Conectamos nutrizes a bancos de leite e pontos de coleta para que mais bebês recebam o alimento que precisam.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

private data class PassoComoFunciona(val texto: String)

@Composable
private fun ComoFuncionaPage() {
    val passos = listOf(
        PassoComoFunciona("Você se cadastra e faz a triagem."),
        PassoComoFunciona("Agendamos a coleta ou você leva até um ponto de coleta."),
        PassoComoFunciona("Seu leite chega a quem mais precisa.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Como funciona",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        passos.forEachIndexed { index, passo ->
            Row(
                modifier = Modifier.padding(bottom = 22.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Text(
                    text = passo.texto,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun ConectaBlhPage(
    onJaFazParte: () -> Unit,
    onNovaDoadora: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Antes de tudo, precisamos saber:",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Você já faz parte do programa Conecta BLH?",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp)
        )

        OpcaoConectaBlh(
            titulo = "Sim, já faço parte do Conecta BLH",
            subtitulo = "Já sou doadora ou tenho cadastro.",
            onClick = onJaFazParte
        )
        OpcaoConectaBlh(
            titulo = "Não, sou nova aqui",
            subtitulo = "Tenho interesse em doar e quero saber mais.",
            onClick = onNovaDoadora,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
private fun OpcaoConectaBlh(
    titulo: String,
    subtitulo: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(text = titulo, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(
                    text = subtitulo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                )
            }
        }
    }
}
