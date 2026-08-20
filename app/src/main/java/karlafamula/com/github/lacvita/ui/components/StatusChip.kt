package karlafamula.com.github.lacvita.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import karlafamula.com.github.lacvita.model.StatusColeta
import karlafamula.com.github.lacvita.ui.theme.LacVitaError
import karlafamula.com.github.lacvita.ui.theme.LacVitaSuccess
import karlafamula.com.github.lacvita.ui.theme.LacVitaWarning

/**
 * Pequeno rótulo colorido que indica o status de uma coleta (agendada, concluída ou cancelada).
 */
@Composable
fun StatusChip(status: StatusColeta, modifier: Modifier = Modifier) {
    val (texto, cor) = when (status) {
        StatusColeta.AGENDADA -> "Agendada" to LacVitaWarning
        StatusColeta.CONCLUIDA -> "Concluída" to LacVitaSuccess
        StatusColeta.CANCELADA -> "Cancelada" to LacVitaError
    }
    Text(
        text = texto,
        color = Color.White,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier
            .background(cor, RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}
