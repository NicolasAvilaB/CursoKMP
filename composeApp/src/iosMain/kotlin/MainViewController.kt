import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import ui.di.AppModule


fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(AppModule())
    }.koin
}