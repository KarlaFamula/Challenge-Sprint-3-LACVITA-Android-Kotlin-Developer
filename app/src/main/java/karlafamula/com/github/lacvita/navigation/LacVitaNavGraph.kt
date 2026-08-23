package karlafamula.com.github.lacvita.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import karlafamula.com.github.lacvita.ui.screens.cadastro.DadosPessoaisScreen
import karlafamula.com.github.lacvita.ui.screens.centralsaude.CentralSaudeScreen
import karlafamula.com.github.lacvita.ui.screens.chat.ChatScreen
import karlafamula.com.github.lacvita.ui.screens.coletas.AgendamentoScreen
import karlafamula.com.github.lacvita.ui.screens.faq.FaqScreen
import karlafamula.com.github.lacvita.ui.screens.historico.HistoricoDoacoesScreen
import karlafamula.com.github.lacvita.ui.screens.home.HomeScreen
import karlafamula.com.github.lacvita.ui.screens.impacto.ImpactoScreen
import karlafamula.com.github.lacvita.ui.screens.login.LoginScreen
import karlafamula.com.github.lacvita.ui.screens.mapa.DetalheBlhScreen
import karlafamula.com.github.lacvita.ui.screens.mapa.MapaScreen
import karlafamula.com.github.lacvita.ui.screens.notificacoes.NotificacoesScreen
import karlafamula.com.github.lacvita.ui.screens.onboarding.OnboardingScreen
import karlafamula.com.github.lacvita.ui.screens.perfil.PerfilNutrizScreen
import karlafamula.com.github.lacvita.ui.screens.proximospassos.ProximosPassosScreen
import karlafamula.com.github.lacvita.ui.screens.splash.SplashScreen
import karlafamula.com.github.lacvita.ui.screens.triagem.TriagemScreen

/**
 * Grafo de navegação central do LacVita. Concentra todas as rotas, a
 * passagem de parâmetros entre telas (ex.: id do BLH selecionado no mapa) e
 * a navegação entre as 5 abas principais (Início, Coletas, Chat, Impacto,
 * Perfil), que compartilham a mesma barra de navegação inferior.
 */
@Composable
fun LacVitaNavGraph(
    navController: NavHostController,
    onExitApp: () -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route


    fun navegarParaAba(rota: String) {
        navController.navigate(rota) {
            popUpTo(LacVitaDestinations.HOME) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(navController = navController, startDestination = LacVitaDestinations.SPLASH) {

        composable(LacVitaDestinations.SPLASH) {
            SplashScreen(onContinuar = {
                navController.navigate(LacVitaDestinations.ONBOARDING) {
                    popUpTo(LacVitaDestinations.SPLASH) { inclusive = true }
                }
            })
        }

        composable(LacVitaDestinations.ONBOARDING) {
            OnboardingScreen(
                onJaFazParte = { navController.navigate(LacVitaDestinations.LOGIN) },
                onNovaDoadora = { navController.navigate(LacVitaDestinations.CADASTRO_DADOS) }
            )
        }

        composable(LacVitaDestinations.LOGIN) {
            LoginScreen(
                onEntrar = {
                    navController.navigate(LacVitaDestinations.HOME) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
                onCriarConta = { navController.navigate(LacVitaDestinations.CADASTRO_DADOS) },
                onBackClick = {
                    if (!navController.popBackStack()) onExitApp()
                }
            )
        }

        composable(LacVitaDestinations.CADASTRO_DADOS) {
            DadosPessoaisScreen(
                onContinuar = { navController.navigate(LacVitaDestinations.TRIAGEM) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(LacVitaDestinations.TRIAGEM) {
            TriagemScreen(
                onContinuar = { navController.navigate(LacVitaDestinations.PROXIMOS_PASSOS) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(LacVitaDestinations.PROXIMOS_PASSOS) {
            ProximosPassosScreen(
                onContinuar = {
                    navController.navigate(LacVitaDestinations.HOME) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // --- Abas principais (bottom navigation) -------------------------------

        composable(LacVitaDestinations.HOME) {
            HomeScreen(
                currentRoute = currentRoute,
                onNavigateTab = ::navegarParaAba,
                onEncontrarPonto = { navController.navigate(LacVitaDestinations.PONTOS_COLETA) },
                onCentralSaudeClick = { navController.navigate(LacVitaDestinations.CENTRAL_SAUDE) },
                onFaqClick = { navController.navigate(LacVitaDestinations.FAQ) },
                onNotificacoesClick = { navController.navigate(LacVitaDestinations.NOTIFICACOES) }
            )
        }

        composable(LacVitaDestinations.COLETAS) {
            AgendamentoScreen(
                currentRoute = currentRoute,
                onNavigateTab = ::navegarParaAba,
                onVerPontosDeColeta = { navController.navigate(LacVitaDestinations.PONTOS_COLETA) }
            )
        }

        composable(LacVitaDestinations.CHAT) {
            ChatScreen(
                currentRoute = currentRoute,
                onNavigateTab = ::navegarParaAba
            )
        }

        composable(LacVitaDestinations.IMPACTO) {
            ImpactoScreen(
                currentRoute = currentRoute,
                onNavigateTab = ::navegarParaAba,
                onHistoricoClick = { navController.navigate(LacVitaDestinations.HISTORICO) }
            )
        }

        composable(LacVitaDestinations.PERFIL) {
            PerfilNutrizScreen(
                currentRoute = currentRoute,
                onNavigateTab = ::navegarParaAba,
                onHistoricoClick = { navController.navigate(LacVitaDestinations.HISTORICO) },
                onFaqClick = { navController.navigate(LacVitaDestinations.FAQ) },
                onSair = {
                    navController.navigate(LacVitaDestinations.LOGIN) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }
            )
        }

        // --- Telas secundárias ---------------------------------------------------

        composable(LacVitaDestinations.PONTOS_COLETA) {
            MapaScreen(
                onBackClick = { navController.popBackStack() },
                onBlhClick = { blhId ->
                    navController.navigate(LacVitaDestinations.detalheBlhRoute(blhId))
                }
            )
        }

        composable(
            route = LacVitaDestinations.DETALHE_BLH_ROUTE,
            arguments = listOf(navArgument(LacVitaDestinations.DETALHE_BLH_ARG) { type = NavType.IntType })
        ) { entry ->
            val blhId = entry.arguments?.getInt(LacVitaDestinations.DETALHE_BLH_ARG) ?: -1
            DetalheBlhScreen(
                blhId = blhId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(LacVitaDestinations.NOTIFICACOES) {
            NotificacoesScreen(onBackClick = { navController.popBackStack() })
        }

        composable(LacVitaDestinations.FAQ) {
            FaqScreen(onBackClick = { navController.popBackStack() })
        }

        composable(LacVitaDestinations.CENTRAL_SAUDE) {
            CentralSaudeScreen(onBackClick = { navController.popBackStack() })
        }

        composable(LacVitaDestinations.HISTORICO) {
            HistoricoDoacoesScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
