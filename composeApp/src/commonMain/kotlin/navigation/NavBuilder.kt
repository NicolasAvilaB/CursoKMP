package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.path
import presentation.expensescreen.ExpensesViewModel
import presentation.expensescreen.events.ExpensesUiState
import theme.model.DarkModeColors
import ui.addexpensesscreen.AddExpensesScreen
import ui.expensescreen.ExpensesScreen
import ui.expensescreen.model.ExpensesCategory
import ui.editexpensesscreen.EditExpensesScreen

@Composable
internal fun navExpenses(
    viewModel: ExpensesViewModel,
    colors: DarkModeColors,
    navGo: NavGo,
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
        navGo.editExpenses.invoke(expense.id.toString())
    }
}

@Composable
internal fun navAddExpenses(
    viewModel: ExpensesViewModel,
    backStackEntry: BackStackEntry,
    categoryList: List<ExpensesCategory>,
    colors: DarkModeColors,
    navGo: NavGo
) {
    val idFromPath = backStackEntry.path<Long>("id")
    val expensesEdit = idFromPath?.let { id ->
        viewModel.getExpensesWithId(id)
    }
    AddExpensesScreen(
        viewModel = viewModel,
        expensesEdit = expensesEdit,
        categoryList = categoryList,
        colors = colors,
        navGo = navGo,
    )
}

@Composable
internal fun navEditExpenses(
    viewModel: ExpensesViewModel,
    backStackEntry: BackStackEntry,
    categoryList: List<ExpensesCategory>,
    colors: DarkModeColors,
    navGo: NavGo
) {
    val idFromPath = backStackEntry.path<Long>("id")
    val expensesEdit = idFromPath?.let { id ->
        viewModel.getExpensesWithId(id)
    }
    EditExpensesScreen(
        viewModel = viewModel,
        expensesEdit = expensesEdit,
        categoryList = categoryList,
        colors = colors,
        navGo = navGo,
    )
}