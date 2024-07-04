import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExampleTest {

    @Test
    fun test() {
        val x = 5
        val y = 10
        val result = x + y
        assertEquals(15, result)

    }

    @Test
    fun test_fail() {
        val x = 5
        val y = 10
        val result = x + y
        assertNotEquals(10, result)

    }

    @Test
    fun expense_model_list_test() {
        val expensesList = mutableListOf<Expenses>()
        val expense = Expenses(
            id = 1,
            description = "Rent",
            amount = 4.5,
            category = ExpensesCategory.CAR,
            title = "Expense"
        )

        expensesList.add(expense)
        assertContains(expensesList, expense)

    }

    @Test
    fun expense_model_list_test_success() {
        val expensesList = mutableListOf<Expenses>()
        val expense = Expenses(
            id = 1,
            description = "Rent",
            amount = 4.5,
            category = ExpensesCategory.CAR,
            title = "Expense"
        )
        val expense2 = Expenses(
            id = 2,
            description = "Rent",
            amount = 15.5,
            category = ExpensesCategory.CAR,
            title = "Expense"
        )

        expensesList.add(expense)
        expensesList.add(expense2)

        assertEquals(expensesList[0].category, expensesList[1].category)

    }


    @Test
    fun expense_model_list_test_fail() {
        val expensesList = mutableListOf<Expenses>()
        val expense = Expenses(
            id = 1,
            description = "Rent",
            amount = 4.5,
            category = ExpensesCategory.CAR,
            title = "Expense"
        )
        val expense2 = Expenses(
            id = 2,
            description = "Rent",
            amount = 15.5,
            category = ExpensesCategory.OTHER,
            title = "Expense"
        )

        expensesList.add(expense)
        expensesList.add(expense2)

        assertNotEquals(expensesList[0].category, expensesList[1].category)

    }
}