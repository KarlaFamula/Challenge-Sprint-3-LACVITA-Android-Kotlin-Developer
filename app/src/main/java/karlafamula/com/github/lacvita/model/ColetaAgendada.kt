package karlafamula.com.github.lacvita.model

enum class StatusColeta {
    AGENDADA, CONCLUIDA, CANCELADA
}


data class ColetaAgendada(
    val id: Int,
    val data: String,
    val horario: String,
    val blhNome: String,
    val litros: Double?,
    val status: StatusColeta
)
