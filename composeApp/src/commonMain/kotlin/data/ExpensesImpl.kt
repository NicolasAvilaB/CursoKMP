package data

import data.model.ExpensesManager
import ui.model.Expenses
import ui.model.ExpensesCategory

class ExpensesImpl(
    private val expensesManager: ExpensesManager
): ExpensesRepository {
    override fun getAllEmpenses(): List<Expenses> {
        return expensesManager.fakeExpenseList
    }

    override fun addExpense(expense: Expenses) {
        expensesManager.addExpense(expense)
    }

    override fun editExpense(expense: Expenses) {
        expensesManager.editExpense(expense)
    }

    override fun getCategoriesExpenses(): List<ExpensesCategory> {
        return expensesManager.getCategoryExpenses()
    }

}