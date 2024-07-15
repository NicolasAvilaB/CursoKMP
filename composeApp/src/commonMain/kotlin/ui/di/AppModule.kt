package ui.di

import com.example.Database
import data.ExpensesImpl
import data.ExpensesRepository
import org.koin.dsl.module
import presentation.expensescreen.ExpensesViewModel

fun AppModule(
    databases: Database
) = module {
    single <ExpensesRepository> { ExpensesImpl(databases)}
    factory { ExpensesViewModel(get()) }
}