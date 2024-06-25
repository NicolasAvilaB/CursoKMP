package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.ExpensesImpl
import data.model.ExpensesManager
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpensesViewModel
import theme.getColorsTheme

@Composable
fun NavController(
    navigator: Navigator,
    navGo: NavGo
) {
    val colors = getColorsTheme()

    val startDestination: String = NavRoutes.ExpensesScreen.route

    val viewModel = viewModel(
        modelClass = ExpensesViewModel::class
    ) {
        ExpensesViewModel(ExpensesImpl(ExpensesManager))
    }

    NavHost(
        modifier = Modifier.background(color = colors.background),
        navigator = navigator,
        initialRoute = startDestination
    ) {
        scene(
            route = startDestination,
            content = {
                navExpenses(
                    viewModel = viewModel,
                    navGo = navGo
                )
            }
        )

        scene(
            route = NavRoutes.AddExpensesScreen("{id}").route,
            content = { backStackEntry ->
                val idFromPath = backStackEntry.path<Long>("id")
                val isAddExpense = idFromPath?.let { id ->
                    viewModel.getExpensesWithId(id)
                }
            }
        )
    }
}