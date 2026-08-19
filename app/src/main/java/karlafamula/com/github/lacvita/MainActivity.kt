package karlafamula.com.github.lacvita

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import karlafamula.com.github.lacvita.navigation.LacVitaNavGraph
import karlafamula.com.github.lacvita.ui.theme.LacVitaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LacVitaApp()
        }
    }
}

@Composable
fun LacVitaApp() {
    LacVitaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            val activity = LocalContext.current as? Activity
            LacVitaNavGraph(
                navController = navController,
                onExitApp = { activity?.finish() }
            )
        }
    }
}
