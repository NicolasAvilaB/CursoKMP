package presentation.expensescreen.events

import ui.expensescreen.model.Expenses

sealed class ExpensesUiState {
    object LoadingUiState : ExpensesUiState()
    data class DisplayUiState(
        val expenses: List<Expenses> = emptyList(),
        val totalExpenses: Double = 0.0
    ) : ExpensesUiState()
    data class ErrorUiState(val e: Throwable) : ExpensesUiState()

}