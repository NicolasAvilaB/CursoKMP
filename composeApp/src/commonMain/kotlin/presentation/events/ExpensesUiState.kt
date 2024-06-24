package presentation.events

import ui.model.Expenses

sealed class ExpensesUiState {
    object LoadingUiState : ExpensesUiState()
    data class DisplayUiState(
        private val expenses: List<Expenses> = emptyList(),
        private val totalExpenses: Double = 0.0
    ) : ExpensesUiState()
    object ErrorUiState : ExpensesUiState()

}