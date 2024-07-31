package data

import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

interface ExpensesRepository {
    suspend fun getAllEmpenses(): List<Expenses>
    suspend fun addExpense(expense: Expenses)
    suspend fun editExpense(expense: Expenses)
    fun getCategoriesExpenses(): List<ExpensesCategory>
    suspend fun deleteExpense(expense: Expenses)
}