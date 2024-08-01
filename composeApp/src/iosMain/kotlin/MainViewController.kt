import androidx.compose.ui.window.ComposeUIViewController
import data.cache.ConfigDevice
import org.koin.core.context.startKoin
import ui.di.AppModule
import data.ConfigDevice as ConfigDevice1


fun MainViewController() = ComposeUIViewController { App(ConfigDevice1()) }

fun initKoin() {
    startKoin {
        modules(AppModule())
    }.koin
}