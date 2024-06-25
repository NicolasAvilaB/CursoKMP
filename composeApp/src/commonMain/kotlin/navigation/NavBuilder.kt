package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import presentation.ExpensesViewModel
import presentation.events.ExpensesUiState
import ui.ExpensesScreen

@Composable
internal fun navExpenses(
    viewModel: ExpensesViewModel,
    navGo: NavGo
) {
    val uiState = remember {
        viewModel.getAllExpenses()
    }.collectAsStateWithLifecycle(
        initial = ExpensesUiState.DisplayUiState()
    ).value

    ExpensesScreen(uiState = uiState) { expense ->
        navGo.addExpenses.invoke(expense.id.toString())
    }
}