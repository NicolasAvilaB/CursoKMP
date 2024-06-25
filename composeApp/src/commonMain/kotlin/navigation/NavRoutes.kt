package navigation

sealed class NavRoutes(var route: String){
    object ExpensesScreen: NavRoutes("/expensesScreen")
    object AddExpensesScreen: NavRoutes("/addExpenses")
    data class EditExpensesScreen(val id: String): NavRoutes("/editExpenses/${id}")
}