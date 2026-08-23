package karlafamula.com.github.lacvita.model

enum class StatusExame {
    PENDENTE, ENVIADO
}

/**
 * Exame de triagem sorológica (HIV, Hepatites B e C etc.) acompanhado na
 * Central da Saúde da nutriz.
 */
data class Exame(
    val id: Int,
    val nome: String,
    val descricao: String,
    var status: StatusExame,
    val arquivo: String? = null
)
