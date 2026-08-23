package karlafamula.com.github.lacvita.navigation


object LacVitaDestinations {
    // Fluxo de entrada
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val CADASTRO_DADOS = "cadastroDados"
    const val TRIAGEM = "triagem"
    const val PROXIMOS_PASSOS = "proximosPassos"

    // Abas principais (bottom navigation)
    const val HOME = "home"
    const val COLETAS = "coletas"
    const val CHAT = "chat"
    const val IMPACTO = "impacto"
    const val PERFIL = "perfil"

    // Telas secundárias
    const val PONTOS_COLETA = "pontosColeta"
    const val DETALHE_BLH = "detalheBlh"
    const val DETALHE_BLH_ARG = "blhId"
    const val DETALHE_BLH_ROUTE = "$DETALHE_BLH/{$DETALHE_BLH_ARG}"
    const val NOTIFICACOES = "notificacoes"
    const val FAQ = "faq"
    const val CENTRAL_SAUDE = "centralSaude"
    const val HISTORICO = "historico"

    fun detalheBlhRoute(blhId: Int) = "$DETALHE_BLH/$blhId"

    /** Rotas que exibem a barra de navegação inferior. */
    val rotasComBottomBar = setOf(HOME, COLETAS, CHAT, IMPACTO, PERFIL)
}
