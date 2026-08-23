package karlafamula.com.github.lacvita.model

enum class TipoNotificacao {
    LEMBRETE, DICA, CONQUISTA, NOVIDADE, ACESSIBILIDADE
}

/**
 * Notificação exibida na central de notificações do app (lembretes de coleta,
 * dicas, conquistas de doação, novidades da rede de pontos de coleta etc.).
 */
data class Notificacao(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val tempoRelativo: String,
    val tipo: TipoNotificacao
)
