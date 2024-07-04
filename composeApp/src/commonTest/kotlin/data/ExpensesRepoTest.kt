package data

import data.model.ExpensesManager
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExpensesRepoTest {
    private val expensesManager = ExpensesManager
    private val repo = ExpensesImpl(expensesManager)

    @Test
    fun expense_list_is_empty() {
        val expenseList = mutableListOf<Expenses>()
        expenseList.addAll(repo.getAllEmpenses())
        assertTrue(
            expenseList.isNotEmpty()
        )
    }

    @Test
    fun add_new_expense_list() {
        val expenseList = repo.getAllEmpenses()
        repo.addExpense(Expenses(
            amount = 100.0,
            description = "Test Expense",
            category = ExpensesCategory.GROCERIES,
            title = "Groceries"
        ))
        assertContains(
            expenseList, expenseList.find { it.id == 7L }
        )

    }

    @Test
    fun edit_expense_list() {
        val expenseListBefore = repo.getAllEmpenses()
        val newExpenseId = 7L
        repo.addExpense(Expenses(
            amount = 100.0,
            description = "Test Expense",
            category = ExpensesCategory.GROCERIES,
            title = "Groceries"
        ))

        assertNotNull(expenseListBefore.find { it.id == newExpenseId })
        val updateExpense = Expenses(
            id = newExpenseId,
            amount = 10.50,
            description = "Test",
            category = ExpensesCategory.OTHER,
            title = "Ropa"
        )
        repo.editExpense(updateExpense)
        val expenseListAfter = repo.getAllEmpenses()
        assertEquals(updateExpense, expenseListAfter.find { it.id == newExpenseId })
    }

    @Test
    fun get_all_categories() {
        val categoryList = mutableListOf<ExpensesCategory>()

        categoryList.addAll(repo.getCategoriesExpenses())
        assertTrue(
            categoryList.isNotEmpty()
        )
    }

    @Test
    fun check_all_category() {
        val allCategories = listOf(
            ExpensesCategory.GROCERIES,
            ExpensesCategory.SNACKS,
            ExpensesCategory.CAR,
            ExpensesCategory.OTHER,
            ExpensesCategory.COFFEE,
            ExpensesCategory.PARTY,
            ExpensesCategory.HOUSE,
        )

        val repoCategories = repo.getCategoriesExpenses()
        assertEquals(allCategories, repoCategories)

    }
}
