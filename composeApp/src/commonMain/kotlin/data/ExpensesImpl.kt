package data

import com.example.Database
import data.model.ExpensesManager
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

class ExpensesImpl(
    private val databases: Database
): ExpensesRepository {

    private val queries = databases.expensesDbQueries

    override fun getAllEmpenses(): List<Expenses> {
        return queries.selectAll().executeAsList().map {
            Expenses(
                id = it.id,
                title = it.title,
                amount = it.amount,
                category = ExpensesCategory.valueOf(it.categoryName),
                description = it.description
            )
        }
        //return expensesManager.fakeExpenseList
    }

    override fun addExpense(expense: Expenses) {
        queries.transaction {
            queries.insert(
                title = expense.title,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun editExpense(expense: Expenses) {
        queries.transaction {
            queries.update(
                id = expense.id,
                title = expense.title,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun getCategoriesExpenses(): List<ExpensesCategory> {
        return queries.categories().executeAsList().map {
            ExpensesCategory.valueOf(it)
        }

    }

}