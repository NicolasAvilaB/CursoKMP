package data.models

import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

object ExpensesManager {

    private var currentId = 1L

    val fakeExpenseList = mutableListOf(
        Expenses(
            id = currentId++,
            amount = 75.00,
            category = ExpensesCategory.GROCERIES,
            description = "Weekly Groceries",
            title = "Weekly Groceries"
        ),
        Expenses(
            id = currentId++,
            amount = 25.00,
            category = ExpensesCategory.SNACKS,
            description = "Snack",
            title = "Snack"
        ),
        Expenses(
            id = currentId++,
            amount = 1215.00,
            category = ExpensesCategory.CAR,
            description = "Delorean",
            title = "Delorean"
        ),
        Expenses(
            id = currentId++,
            amount = 1215.00,
            category = ExpensesCategory.PARTY,
            description = "Fiesta",
            title = "Fiesta"
        ),
        Expenses(
            id = currentId++,
            amount = 5.00,
            category = ExpensesCategory.HOUSE,
            description = "CLeaning",
            title = "CLeaning"
        ),
        Expenses(
            id = currentId++,
            amount = 121212.00,
            category = ExpensesCategory.OTHER,
            description = "Services",
            title = "Services"
        )
    )

    fun addExpense(expense: Expenses) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expenses) {
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description,
                title = expense.title
            )
        }
    }

    fun getCategoryExpenses(): List<ExpensesCategory> {
        return listOf(
            ExpensesCategory.GROCERIES,
            ExpensesCategory.SNACKS,
            ExpensesCategory.CAR,
            ExpensesCategory.OTHER,
            ExpensesCategory.COFFEE,
            ExpensesCategory.PARTY,
            ExpensesCategory.HOUSE,
        )
    }

}