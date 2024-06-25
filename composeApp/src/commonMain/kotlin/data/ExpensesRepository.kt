package data

import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

interface ExpensesRepository {
    fun getAllEmpenses(): List<Expenses>
    fun addExpense(expense: Expenses)
    fun editExpense(expense: Expenses)
    fun getCategoriesExpenses(): List<ExpensesCategory>
}