package karlafamula.com.github.lacvita.model

/**
 * Representa um dia do mês em que houve doação, usado para pintar o calendário
 * do histórico de doações da nutriz.
 */
data class DiaDoacao(
    val dia: Int,
    val litros: Double
)

/**
 * Resumo consolidado de um mês de doações (usado no card "Resumo do mês").
 */
data class ResumoMensal(
    val mesReferencia: String,
    val totalLitros: Double,
    val totalDoacoes: Int,
    val recemNascidosAlimentados: Int
)
