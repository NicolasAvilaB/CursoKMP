package ui.di

import data.ExpensesImpl
import data.ExpensesRepository
import data.model.ExpensesManager
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import presentation.expensescreen.ExpensesViewModel

fun AppModule() = module {
    single { ExpensesManager }.withOptions{createdAtStart()}
    single <ExpensesRepository> { ExpensesImpl(get())}
    factory { ExpensesViewModel(get()) }

}