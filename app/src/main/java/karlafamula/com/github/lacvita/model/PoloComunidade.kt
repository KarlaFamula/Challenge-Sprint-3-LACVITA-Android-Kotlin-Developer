package karlafamula.com.github.lacvita.model

/**
 * Volume de leite coletado por polo/BLH em uma semana, usado no dashboard de impacto.
 */
data class PoloComunidade(
    val nomePolo: String,
    val litrosNaSemana: Double
)
