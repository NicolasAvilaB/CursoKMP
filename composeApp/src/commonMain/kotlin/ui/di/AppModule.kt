package ui.di

import data.ExpensesRepository
import data.models.ExpensesManager
import data.remote.ExpensesImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import presentation.expensescreen.ExpensesViewModel

fun AppModule() = module {
    single { ExpensesManager }.withOptions { createdAtStart() }
    single<HttpClient> { HttpClient { install(ContentNegotiation) { json() } } }
    single<ExpensesRepository> { ExpensesImpl(get(), get()) }
    factory { ExpensesViewModel(get()) }

}