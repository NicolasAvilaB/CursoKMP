import androidx.compose.runtime.Composable
import data.cache.ConfigDevice
import data.cache.SessionCache
import moe.tlaster.precompose.PreComposeApp
import navigation.NavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import theme.AppTheme

@Composable
@Preview
fun App(
    configDevice: ConfigDevice ?= null
) {
    PreComposeApp {
        KoinContext {
            AppTheme {
                SessionCache.configDevice = configDevice
                NavController()
            }
        }
    }
}
