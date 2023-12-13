import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import ui.screens.home.Home

@Composable
fun App() {
    Navigator( screen = Home)
}