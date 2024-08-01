package data.remote

import data.ExpensesRepository
import data.models.ExpensesManager
import data.models.RemoteListExpenses
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

class ExpensesImpl(
    private val expensesManager: ExpensesManager,
    private val httpClient: HttpClient
) : ExpensesRepository {

    private val BASE_URL = "http://192.168.1.30:8080"

    override suspend fun getAllEmpenses(): List<Expenses> {
        val networkResponse = httpClient.get("$BASE_URL/expenses").body<List<RemoteListExpenses>>()
        if (networkResponse.isEmpty()) return emptyList()
        return networkResponse.map { netExpenses ->
            Expenses(
                id = netExpenses.id,
                title = netExpenses.title,
                amount = netExpenses.amount,
                description = netExpenses.description,
                category = ExpensesCategory.valueOf(netExpenses.categoryName)
            )
        }
    }

    override suspend fun addExpense(expense: Expenses) {
        httpClient.post("$BASE_URL/expenses") {
            contentType(ContentType.Application.Json)
            setBody(
                RemoteListExpenses(
                    title = expense.title,
                    amount = expense.amount,
                    description = expense.description,
                    categoryName = expense.category.name
                )
            )
        }
    }

    override suspend fun editExpense(expense: Expenses) {
        httpClient.put("$BASE_URL/expenses/${expense.id}") {
            contentType(ContentType.Application.Json)
            setBody(
                RemoteListExpenses(
                    id = expense.id,
                    title = expense.title,
                    amount = expense.amount,
                    description = expense.description,
                    categoryName = expense.category.name
                )
            )
        }
    }

    override fun getCategoriesExpenses(): List<ExpensesCategory> {
        return expensesManager.getCategoryExpenses()
    }

    override suspend fun deleteExpense(id: Long) {
        httpClient.delete("$BASE_URL/expenses/${id}")
    }
}