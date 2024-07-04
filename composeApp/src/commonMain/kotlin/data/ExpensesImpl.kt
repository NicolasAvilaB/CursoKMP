package data

import com.example.Database
import data.model.ExpensesManager
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

class ExpensesImpl(
    private val expensesManager: ExpensesManager,
    private val databases: Database
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