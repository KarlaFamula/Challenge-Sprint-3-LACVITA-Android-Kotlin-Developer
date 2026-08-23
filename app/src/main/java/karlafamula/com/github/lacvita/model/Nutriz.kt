package karlafamula.com.github.lacvita.model

/**
 * Perfil da doadora (nutriz) cadastrada no app.
 */
data class Nutriz(
    val nome: String,
    val tipoDoadora: String,
    val cidade: String,
    val totalLitrosDoados: Double,
    val bebesImpactados: Int,
    val doacoesRealizadas: Int
)
