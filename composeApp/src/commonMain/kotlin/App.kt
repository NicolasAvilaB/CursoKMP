import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.NavController
import navigation.NavGo
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.AppTheme
import theme.getColorsTheme

@Composable
@Preview
fun App() {
    PreComposeApp {
        AppTheme {
            NavController()
        }
    }
}
