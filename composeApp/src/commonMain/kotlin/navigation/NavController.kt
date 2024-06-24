package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import theme.getColorsTheme
import ui.ExpensesScreen

@Composable
fun NavController(){

    val colors = getColorsTheme()
    val startDestination: String = NavRoutes.ExpensesScreen.routes

    NavHost(
        modifier = Modifier.background(color = colors.background),
        navigator = Navigator(),
        initialRoute = startDestination
    ) {
        scene(
            route = NavRoutes.ExpensesScreen.routes,
            content = {
                //ExpensesScreen()
            }
        )
    }
}