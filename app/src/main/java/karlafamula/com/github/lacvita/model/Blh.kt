package karlafamula.com.github.lacvita.model

/**
 * Representa um Banco de Leite Humano (BLH) ou posto de coleta parceiro da rede.
 */
data class Blh(
    val id: Int,
    val nome: String,
    val cidade: String,
    val estado: String,
    val endereco: String,
    val horarioFuncionamento: String,
    val distanciaKm: Double,
    val aceitaColetaDomiciliar: Boolean,
    val telefone: String,
    val vagasHoje: Int
)
