package presentation.expensescreen

import data.ExpensesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import presentation.expensescreen.events.ExpensesUiState
import presentation.expensescreen.events.ExpensesUiState.DisplayUiState
import presentation.expensescreen.events.ExpensesUiState.ErrorUiState
import presentation.expensescreen.events.ExpensesUiState.LoadingUiState
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

class ExpensesViewModel(
    private val expensesRepository: ExpensesRepository
) : ViewModel() {
    private val defaultUiState: ExpensesUiState = LoadingUiState
    private val _uiState: MutableStateFlow<ExpensesUiState> =
        MutableStateFlow(defaultUiState)

    private fun refreshListState(){
        viewModelScope.launch{
            runCatching {
                val allExpenses = expensesRepository.getAllEmpenses()
                _uiState.value = DisplayUiState(
                    expenses = allExpenses,
                    totalExpenses = allExpenses.sumOf { it.amount }
                )
            }.onFailure { e->
                _uiState.value = ErrorUiState(e = e)
            }
        }
    }

    fun getAllExpenses(): StateFlow<ExpensesUiState> {
        _uiState.value = defaultUiState
        viewModelScope.launch {
            refreshListState()
        }
        return _uiState
    }

    fun addExpense(expense: Expenses) {
        viewModelScope.launch {
            expensesRepository.addExpense(expense)
            refreshListState()
        }
    }

    fun editExpense(expense: Expenses) {
        viewModelScope.launch {
            expensesRepository.editExpense(expense)
            refreshListState()
        }
    }

    fun deleteExpense(id: Long){
        viewModelScope.launch {
            expensesRepository.deleteExpense(id)
            refreshListState()
        }
    }

    fun getExpensesWithId(id: Long): Expenses? {
        return (_uiState.value as? DisplayUiState)?.expenses?.firstOrNull() { it.id == id}
    }

    fun getCategory(): List<ExpensesCategory>{
        return expensesRepository.getCategoriesExpenses()
    }
}