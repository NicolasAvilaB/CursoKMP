package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import presentation.expensescreen.ExpensesViewModel
import presentation.expensescreen.events.ExpensesUiState
import theme.model.DarkModeColors
import ui.expensescreen.ExpensesScreen
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory
import ui.expenseseditscreen.ExpensesEditScreen

@Composable
internal fun navExpenses(
    viewModel: ExpensesViewModel,
    navGo: NavGo,
    colors: DarkModeColors
) {
    val uiState = remember {
        viewModel.getAllExpenses()
    }.collectAsStateWithLifecycle(
        initial = ExpensesUiState.DisplayUiState()
    ).value

    ExpensesScreen(
        uiState = uiState,
        navGo = navGo,
        colors = colors
    ) { expense ->
        navGo.addExpenses.invoke(expense.id.toString())
    }
}

@Composable
internal fun navEditExpenses(
    expensesEdit: Expenses?,
    categoryList: List<ExpensesCategory>,
    colors: DarkModeColors,
    navGo: NavGo,
    addExpenses: (Expenses) -> Unit
) {
    ExpensesEditScreen(
        expensesEdit = expensesEdit,
        categoryList = categoryList,
        navGo = navGo,
        colors = colors,
        addExpenses = addExpenses
    )
}