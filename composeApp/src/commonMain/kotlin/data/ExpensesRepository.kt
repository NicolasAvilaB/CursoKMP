package data

import ui.model.Expenses
import ui.model.ExpensesCategory

interface ExpensesRepository {
    fun getAllEmpenses(): List<Expenses>
    fun addExpense(expense: Expenses)
    fun editExpense(expense: Expenses)
    fun getCategoriesExpenses(): List<ExpensesCategory>
}