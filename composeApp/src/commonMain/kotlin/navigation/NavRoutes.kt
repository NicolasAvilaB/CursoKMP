package navigation

sealed class NavRoutes(var route: String){
    object ExpensesScreen: NavRoutes("/expensesScreen")
    object NavAddExpensesScreen: NavRoutes("/addExpenses")
    data class AddExpensesScreen(val id: String): NavRoutes("/addExpenses/${id}")
}