package presentation

import data.ExpensesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import presentation.events.ExpensesUiState
import presentation.events.ExpensesUiState.DisplayUiState
import ui.model.Expenses

class ExpensesViewModel(
    private val expensesRepository: ExpensesRepository
) : ViewModel() {

    private val defaultUiState: ExpensesUiState = DisplayUiState()
    private val _uiState: MutableStateFlow<ExpensesUiState> =
        MutableStateFlow(defaultUiState)
    private val allExpenses = expensesRepository.getAllEmpenses()

    private fun changeState(){
        _uiState.value = DisplayUiState(
            expenses = allExpenses,
            totalExpenses = allExpenses.sumOf { it.amount }
        )
    }

    fun getAllExpenses(): StateFlow<ExpensesUiState> {
        _uiState.value = ExpensesUiState.LoadingUiState
        viewModelScope.launch {
            changeState()
        }
        return _uiState
    }

    fun addExpense(expense: Expenses) {
        viewModelScope.launch {
            expensesRepository.addExpense(expense)
            changeState()
        }
    }

    fun editExpense(expense: Expenses) {
        viewModelScope.launch {
            expensesRepository.editExpense(expense)
            changeState()
        }
    }

    fun getExpensesWithId(id: Long): Expenses {
        return allExpenses.first { it.id == id }
    }
}