import androidx.compose.ui.window.ComposeUIViewController
import com.example.Database
import data.remote.DatabasesDriverFactory
import org.koin.core.context.startKoin
import ui.di.AppModule

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(AppModule(Database.invoke(DatabasesDriverFactory().createDriver())))
    }.koin
}