package presentation

import data.ExpensesRepository
import kotlinx.coroutines.flow.MutableStateFlow
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

    init {
        getAllExpenses()
    }

    private fun changeState(){
        _uiState.value = DisplayUiState(
            expenses = allExpenses,
            totalExpenses = allExpenses.sumOf { it.amount }
        )
    }

    private fun getAllExpenses() {
        _uiState.value = ExpensesUiState.LoadingUiState
        viewModelScope.launch {
            changeState()
        }
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