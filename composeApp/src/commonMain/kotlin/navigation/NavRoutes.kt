package navigation

sealed class NavRoutes(var routes: String){
    object ExpensesScreen: NavRoutes("ExpensesScreen")
}