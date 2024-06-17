package data.model

import ui.model.Expenses
import ui.model.ExpensesCategory

object ExpensesManager {

    private var currentId = 1L

    val fakeExpenseList = mutableListOf(
        Expenses(
            id = currentId++,
            amount = 75.00,
            category = ExpensesCategory.GROCERIES,
            description = "Weekly Buy",
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

}