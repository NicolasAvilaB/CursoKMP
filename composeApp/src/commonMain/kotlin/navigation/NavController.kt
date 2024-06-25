package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import data.ExpensesImpl
import data.model.ExpensesManager
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.expensescreen.ExpensesViewModel
import theme.getColorsTheme

@Composable
fun NavController() {

    val navigator = rememberNavigator()

    val navGo = remember(navigator) { NavGo(navigator) }

    val startDestination: String = NavRoutes.ExpensesScreen.route

    val colors = getColorsTheme()

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
                    navGo = navGo,
                    colors = colors
                )
            }
        )

        scene(
            route = NavRoutes.AddExpensesScreen.route,
            content = { backStackEntry ->
                navAddExpenses(
                    viewModel = viewModel,
                    backStackEntry = backStackEntry,
                    categoryList= viewModel.getCategory(),
                    colors = colors,
                    navGo = navGo
                )
            }
        )

        scene(
            route = NavRoutes.EditExpensesScreen("{id}?").route,
            content = { backStackEntry ->
                navEditExpenses(
                    viewModel = viewModel,
                    backStackEntry = backStackEntry,
                    categoryList= viewModel.getCategory(),
                    colors = colors,
                    navGo = navGo
                )
            }
        )
    }
}