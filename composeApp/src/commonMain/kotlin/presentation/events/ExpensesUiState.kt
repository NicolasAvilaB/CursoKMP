package presentation.events

import ui.model.Expenses

sealed class ExpensesUiState {
    object LoadingUiState : ExpensesUiState()
    data class DisplayUiState(
        val expenses: List<Expenses> = emptyList(),
        val totalExpenses: Double = 0.0
    ) : ExpensesUiState()
    object ErrorUiState : ExpensesUiState()

}