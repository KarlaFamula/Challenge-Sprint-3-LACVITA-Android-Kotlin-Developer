package karlafamula.com.github.lacvita.model

enum class Remetente { USUARIA, ASSISTENTE }


data class ChatMessage(
    val id: Int,
    val texto: String,
    val remetente: Remetente
)

/**
 * Pergunta rápida sugerida ao usuário no chat, associada a uma resposta
 * pré-definida simulando o comportamento de um assistente virtual.
 */
data class PerguntaRapida(
    val pergunta: String,
    val resposta: String
)
