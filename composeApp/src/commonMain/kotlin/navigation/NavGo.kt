package navigation

import moe.tlaster.precompose.navigation.Navigator

class NavGo(
    navigator: Navigator
) {
    val detailExpenses: (String) -> Unit = { id ->
        navigator.navigate(NavRoutes.ExpensesScreen.route)
    }

    val addExpenses: (String) -> Unit = { id ->
        navigator.navigate(NavRoutes.AddExpensesScreen(id).route)
    }

    val navAddExpenses: () -> Unit = {
        navigator.navigate(NavRoutes.NavAddExpensesScreen.route)
    }

    val popBackStack: () -> Unit = {
        navigator.popBackStack()
    }
}