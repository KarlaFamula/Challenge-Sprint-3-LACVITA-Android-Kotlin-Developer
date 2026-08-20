package karlafamula.com.github.lacvita.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Tela de login para nutrizes que já fazem parte do Conecta BLH.
 * Usa apenas estado local (sem autenticação real) para manter o fluxo
 * navegável, conforme escopo desta Sprint.
 */
@Composable
fun LoginScreen(
    onEntrar: () -> Unit,
    onCriarConta: () -> Unit,
    onBackClick: () -> Unit
) {
    var emailOuCpf by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Que bom te ver de novo! \uD83D\uDC99",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Faça login para continuar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp, bottom = 28.dp)
            )

            OutlinedTextField(
                value = emailOuCpf,
                onValueChange = { emailOuCpf = it },
                label = { Text("E-mail ou CPF") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            TextButton(onClick = { }, modifier = Modifier.align(Alignment.End)) {
                Text(text = "Esqueci minha senha")
            }

            Button(
                onClick = onEntrar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Entrar")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = "  ou  ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Divider(modifier = Modifier.weight(1f))
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Entrar com Google")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ainda não tem conta?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                TextButton(onClick = onCriarConta) {
                    Text(text = "Criar conta", fontWeight = FontWeight.SemiBold)
                }
            }

            TextButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Voltar")
            }
        }
    }
}
