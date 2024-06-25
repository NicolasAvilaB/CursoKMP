package navigation

import moe.tlaster.precompose.navigation.Navigator

class NavGo(
    navigator: Navigator
) {
    val addExpenses: () -> Unit = {
        navigator.navigate(NavRoutes.AddExpensesScreen.route)
    }

    val editExpenses: (String) -> Unit = { id ->
        navigator.navigate(NavRoutes.EditExpensesScreen(id).route)
    }

    val popBackStack: () -> Unit = {
        navigator.popBackStack()
    }
}