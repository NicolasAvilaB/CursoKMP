import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.AppTheme
import theme.getColorsTheme

@Composable
@Preview
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()
        AppTheme {

        }
    }
}
