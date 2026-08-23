package karlafamula.com.github.lacvita.mock

import karlafamula.com.github.lacvita.model.Blh
import karlafamula.com.github.lacvita.model.ChatMessage
import karlafamula.com.github.lacvita.model.ColetaAgendada
import karlafamula.com.github.lacvita.model.DiaDoacao
import karlafamula.com.github.lacvita.model.Exame
import karlafamula.com.github.lacvita.model.FaqItem
import karlafamula.com.github.lacvita.model.Notificacao
import karlafamula.com.github.lacvita.model.Nutriz
import karlafamula.com.github.lacvita.model.PassoGuia
import karlafamula.com.github.lacvita.model.PerguntaRapida
import karlafamula.com.github.lacvita.model.PoloComunidade
import karlafamula.com.github.lacvita.model.Remetente
import karlafamula.com.github.lacvita.model.ResumoMensal
import karlafamula.com.github.lacvita.model.StatusColeta
import karlafamula.com.github.lacvita.model.StatusExame
import karlafamula.com.github.lacvita.model.TipoNotificacao

/**
 * Fonte única de dados mockados do LacVita.
 *
 * Nesta Sprint não há integração com API, Firebase ou banco de dados: todos os dados
 * abaixo simulam o comportamento real da Rede Brasileira de Bancos de Leite Humano (rBLH/Fiocruz)
 * e do programa Lactare, servindo de base para as telas do aplicativo.
 */
object MockDataProvider {

    val blhs = listOf(
        Blh(
            id = 1,
            nome = "BLH Hospital Pérola Byington",
            cidade = "São Paulo",
            estado = "SP",
            endereco = "Av. Brigadeiro Luís Antônio, 683 - Bela Vista",
            horarioFuncionamento = "Seg. a Sex., 07h às 17h",
            distanciaKm = 2.4,
            aceitaColetaDomiciliar = true,
            telefone = "(11) 2179-3900",
            vagasHoje = 6
        ),
        Blh(
            id = 2,
            nome = "BLH Maternidade Escola Assis Chateaubriand",
            cidade = "Fortaleza",
            estado = "CE",
            endereco = "Rua Cônego Pinheiro, 135 - Porangabuçu",
            horarioFuncionamento = "Seg. a Sáb., 07h às 18h",
            distanciaKm = 5.1,
            aceitaColetaDomiciliar = true,
            telefone = "(85) 3366-8081",
            vagasHoje = 3
        ),
        Blh(
            id = 3,
            nome = "BLH Instituto Fernandes Figueira",
            cidade = "Rio de Janeiro",
            estado = "RJ",
            endereco = "Av. Rui Barbosa, 716 - Flamengo",
            horarioFuncionamento = "Seg. a Sex., 08h às 16h",
            distanciaKm = 7.8,
            aceitaColetaDomiciliar = false,
            telefone = "(21) 2554-1100",
            vagasHoje = 0
        ),
        Blh(
            id = 4,
            nome = "BLH Hospital de Clínicas de Porto Alegre",
            cidade = "Porto Alegre",
            estado = "RS",
            endereco = "Rua Ramiro Barcelos, 2350 - Santa Cecília",
            horarioFuncionamento = "Seg. a Sex., 07h30 às 17h",
            distanciaKm = 3.6,
            aceitaColetaDomiciliar = true,
            telefone = "(51) 3359-8000",
            vagasHoje = 9
        ),
        Blh(
            id = 5,
            nome = "BLH Instituto Materno Infantil de Pernambuco",
            cidade = "Recife",
            estado = "PE",
            endereco = "Rua dos Coelhos, 300 - Boa Vista",
            horarioFuncionamento = "Seg. a Sex., 07h às 15h",
            distanciaKm = 4.2,
            aceitaColetaDomiciliar = true,
            telefone = "(81) 3184-3200",
            vagasHoje = 2
        ),
        Blh(
            id = 6,
            nome = "BLH Hospital Regional de Taguatinga",
            cidade = "Brasília",
            estado = "DF",
            endereco = "Área Especial, QNC 3 - Taguatinga",
            horarioFuncionamento = "Seg. a Sex., 07h às 16h",
            distanciaKm = 11.3,
            aceitaColetaDomiciliar = false,
            telefone = "(61) 3352-4500",
            vagasHoje = 4
        )
    )

    val nutrizLogada = Nutriz(
        nome = "Juliana Silva",
        tipoDoadora = "Doadora ativa",
        cidade = "São Paulo - SP",
        totalLitrosDoados = 12.0,
        bebesImpactados = 8,
        doacoesRealizadas = 9
    )

    val historicoColetas = listOf(
        ColetaAgendada(1, "Terça-feira, 21/06", "14:00", "Coleta em casa", null, StatusColeta.AGENDADA),
        ColetaAgendada(2, "15/06/2026", "10:30", "BLH Hospital Pérola Byington", 0.9, StatusColeta.CONCLUIDA),
        ColetaAgendada(3, "09/06/2026", "08:00", "BLH Hospital Pérola Byington", 1.1, StatusColeta.CONCLUIDA),
        ColetaAgendada(4, "02/06/2026", "09:30", "Coleta em casa", 0.8, StatusColeta.CONCLUIDA),
        ColetaAgendada(5, "18/05/2026", "14:00", "BLH Hospital Pérola Byington", null, StatusColeta.CANCELADA)
    )

    /** Próxima coleta agendada da nutriz logada (usada no card da Home). */
    val proximaColeta = historicoColetas.first { it.status == StatusColeta.AGENDADA }

    val faqs = listOf(
        FaqItem(
            "Quem pode doar leite humano?",
            "Qualquer nutriz saudável, que esteja amamentando ou ordenhando, sem uso de medicamentos incompatíveis e sem doenças infectocontagiosas, pode se tornar doadora."
        ),
        FaqItem(
            "Doar leite prejudica a amamentação do meu bebê?",
            "Não. A doação só é orientada quando há produção de leite acima da necessidade do próprio filho, sem prejudicar sua amamentação."
        ),
        FaqItem(
            "Preciso pagar para doar ou coletar o material?",
            "Não. Todo o processo é gratuito: os frascos são fornecidos pelo banco de leite e, quando disponível, a coleta domiciliar também é gratuita."
        ),
        FaqItem(
            "Como o leite doado é armazenado antes da coleta?",
            "O leite deve ser congelado em freezer comum (não no compartimento da porta) em recipiente de vidro esterilizado, identificado com data da ordenha."
        ),
        FaqItem(
            "Quantos bebês um litro de leite pode alimentar?",
            "Em média, 1 litro de leite humano pode alimentar até 10 bebês por dia em uma UTI neonatal."
        ),
        FaqItem(
            "Posso doar mesmo tomando remédios de uso contínuo?",
            "Depende do medicamento. A triagem inicial do app já sinaliza quando é necessário confirmar a compatibilidade com a equipe do banco de leite."
        )
    )

    val passosGuiaDoacao = listOf(
        PassoGuia(
            "1. Faça a triagem inicial",
            "Responda ao questionário rápido do LacVita para verificar se você está apta a doar neste momento."
        ),
        PassoGuia(
            "2. Higienize e armazene o leite",
            "Lave bem as mãos, use frasco de vidro esterilizado e congele o leite ordenhado identificado com a data."
        ),
        PassoGuia(
            "3. Escolha um BLH ou peça coleta domiciliar",
            "Use o mapa inteligente do app para encontrar o posto mais próximo ou solicitar a busca em casa, quando disponível."
        ),
        PassoGuia(
            "4. Agende sua doação",
            "Escolha um horário disponível diretamente no app e receba a confirmação e lembretes por notificação."
        ),
        PassoGuia(
            "5. Acompanhe seu impacto",
            "Depois da coleta, seu histórico e a quantidade de bebês nutridos são atualizados no seu perfil."
        )
    )

    val requisitosMedicos = listOf(
        "Estar amamentando ou em processo de ordenha",
        "Não fumar nem consumir bebidas alcoólicas",
        "Não estar em uso de medicamentos incompatíveis com a amamentação",
        "Não ter diagnóstico de HIV, HTLV, sífilis ou hepatite B/C",
        "Realizar os exames pré-natais de rotina em dia"
    )

    val polosComunidade = listOf(
        PoloComunidade("São Paulo - SP", 42.5),
        PoloComunidade("Fortaleza - CE", 27.8),
        PoloComunidade("Rio de Janeiro - RJ", 33.1),
        PoloComunidade("Porto Alegre - RS", 21.4),
        PoloComunidade("Recife - PE", 18.9),
        PoloComunidade("Brasília - DF", 15.6)
    )

    const val totalBebesNutridosMes = 1240
    const val totalLitrosColetadosMes = 159.3

    fun blhPorId(id: Int): Blh? = blhs.firstOrNull { it.id == id }

    // ---------------------------------------------------------------------
    // Onboarding
    // ---------------------------------------------------------------------

    val dicaDoDia = "Hidrate-se bastante e mantenha uma alimentação equilibrada antes da ordenha."

    // ---------------------------------------------------------------------
    // Notificações
    // ---------------------------------------------------------------------

    val notificacoes = listOf(
        Notificacao(
            id = 1,
            titulo = "Lembrete de coleta",
            descricao = "Sua coleta está agendada para 21/06 às 14h.",
            tempoRelativo = "10:30",
            tipo = TipoNotificacao.LEMBRETE
        ),
        Notificacao(
            id = 2,
            titulo = "Dica para você",
            descricao = "Mantenha uma alimentação equilibrada e beba água.",
            tempoRelativo = "Ontem",
            tipo = TipoNotificacao.DICA
        ),
        Notificacao(
            id = 3,
            titulo = "Parabéns!",
            descricao = "Você alcançou 10 litros doados!",
            tempoRelativo = "2 dias",
            tipo = TipoNotificacao.CONQUISTA
        ),
        Notificacao(
            id = 4,
            titulo = "Novidade",
            descricao = "Novo ponto de coleta disponível perto de você.",
            tempoRelativo = "3 dias",
            tipo = TipoNotificacao.NOVIDADE
        ),
        Notificacao(
            id = 5,
            titulo = "Acessibilidade",
            descricao = "Você ativou a audiodescrição.",
            tempoRelativo = "3 dias",
            tipo = TipoNotificacao.ACESSIBILIDADE
        )
    )

    // ---------------------------------------------------------------------
    // Central da saúde (exames)
    // ---------------------------------------------------------------------

    val examesPendentes = listOf(
        Exame(
            id = 1,
            nome = "HIV",
            descricao = "Teste sorológico obrigatório para doadoras de leite humano.",
            status = StatusExame.PENDENTE
        ),
        Exame(
            id = 2,
            nome = "Hepatites B e C",
            descricao = "HBsAg e Anti-HCV, exigidos a cada ciclo de doação.",
            status = StatusExame.PENDENTE
        )
    )

    // ---------------------------------------------------------------------
    // Chat / assistente virtual
    // ---------------------------------------------------------------------

    val perguntasRapidas = listOf(
        PerguntaRapida(
            "Como armazenar o leite?",
            "Congele o leite ordenhado em vidro esterilizado, identificado com data e horário, no fundo do freezer (nunca na porta)."
        ),
        PerguntaRapida(
            "Posso tomar remédio?",
            "Depende do medicamento. Registre o que você utiliza na triagem para que a equipe do banco de leite confirme a compatibilidade."
        ),
        PerguntaRapida(
            "Como funciona a coleta?",
            "Você escolhe entre coleta em casa ou levar até um ponto parceiro, agenda um horário e recebe a confirmação no app."
        )
    )

    val mensagensChatAssistente = listOf(
        ChatMessage(
            id = 1,
            texto = "Olá! Sou o assistente virtual do LacVita. Como posso te ajudar hoje?",
            remetente = Remetente.ASSISTENTE
        )
    )

    // ---------------------------------------------------------------------
    // Histórico de doações (calendário)
    // ---------------------------------------------------------------------

    val diasDoacaoJunho2026 = listOf(
        DiaDoacao(dia = 2, litros = 0.8),
        DiaDoacao(dia = 9, litros = 1.1),
        DiaDoacao(dia = 15, litros = 0.9),
        DiaDoacao(dia = 21, litros = 0.0) // agendada, ainda não coletada
    )

    val resumoJunho2026 = ResumoMensal(
        mesReferencia = "Junho de 2026",
        totalLitros = 10.8,
        totalDoacoes = 9,
        recemNascidosAlimentados = 7
    )

    // ---------------------------------------------------------------------
    // Endereços salvos (perfil)
    // ---------------------------------------------------------------------

    val enderecosSalvos = listOf(
        "Casa – R. Lins de Vasconcelos, 110, Vila Mariana, São Paulo - SP",
        "Trabalho – Av. Paulista, 900, Bela Vista, São Paulo - SP"
    )
}
