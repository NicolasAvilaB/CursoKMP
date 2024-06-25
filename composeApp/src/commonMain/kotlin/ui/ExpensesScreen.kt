package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.events.ExpensesUiState
import theme.getColorsTheme
import ui.components.AllExpensesHeader
import ui.components.ExpensedTotalHeader
import ui.components.ExpensesItem
import ui.model.Expenses

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesScreen(
    uiState: ExpensesUiState,
    onExpensesClick: (expenses: Expenses) -> Unit
) {
    val colors = getColorsTheme()

    when (uiState) {
        is ExpensesUiState.LoadingUiState -> { Text("Loading") }
        is ExpensesUiState.DisplayUiState -> {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val stateValue = uiState
                stickyHeader {
                    Column(modifier = Modifier.background(colors.background)) {
                        ExpensedTotalHeader(stateValue.totalExpenses)
                        AllExpensesHeader()
                    }
                }
                stateValue.expenses?.let { listItems ->
                    items(listItems.size) { index ->
                        val itemExpense = listItems[index]
                        ExpensesItem(
                            expenses = itemExpense,
                            onExpensesClick = onExpensesClick
                        )
                    }
                }
            }
        }
        is ExpensesUiState.ErrorUiState -> { Text("Error") }
    }
}

