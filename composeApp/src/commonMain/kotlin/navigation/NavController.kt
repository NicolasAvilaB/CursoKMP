package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import data.ExpensesImpl
import data.model.ExpensesManager
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
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
            route = NavRoutes.AddExpensesScreen("{id}?").route,
            content = { backStackEntry ->
                val idFromPath = backStackEntry.path<Long>("id")
                val expensesEdit = idFromPath?.let { id ->
                    viewModel.getExpensesWithId(id)
                }
                navEditExpenses(
                    expensesEdit = expensesEdit,
                    navGo = navGo,
                ) { expens ->
                    if (expensesEdit == null) {
                        viewModel.addExpense(expens)
                    } else {
                        viewModel.editExpense(expens)
                    }
                    navGo.popBackStack.invoke()
                }
            }
        )
    }
}