package karlafamula.com.github.lacvita.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Pergunta com duas opções (Sim/Não) em formato de botões segmentados,
 * usada nas telas de cadastro e triagem para respostas rápidas.
 */
@Composable
fun SimNaoSelector(
    pergunta: String,
    valor: Boolean?,
    onValorChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = pergunta,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (valor == true) {
                Button(
                    onClick = { onValorChange(true) },
                    modifier = Modifier.weight(1f).height(44.dp),
                    shape = RoundedCornerShape(10.dp)
                ) { Text("Sim") }
            } else {
                OutlinedButton(
                    onClick = { onValorChange(true) },
                    modifier = Modifier.weight(1f).height(44.dp),
                    shape = RoundedCornerShape(10.dp)
                ) { Text("Sim") }
            }

            if (valor == false) {
                Button(
                    onClick = { onValorChange(false) },
                    modifier = Modifier.weight(1f).height(44.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors()
                ) { Text("Não") }
            } else {
                OutlinedButton(
                    onClick = { onValorChange(false) },
                    modifier = Modifier.weight(1f).height(44.dp),
                    shape = RoundedCornerShape(10.dp)
                ) { Text("Não") }
            }
        }
    }
}
