package com.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ui.expensescreen.components.AllExpensesHeader
import ui.expensescreen.components.ExpensedTotalHeader
import ui.expensescreen.components.ExpensesItem
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

@Preview(showBackground = true)
@Composable
fun ExpensedTotalHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ExpensedTotalHeader(total = 1028.4)
    }
}


@Preview(showBackground = true)
@Composable
fun AllExpensesHeaderPreview() {
    AllExpensesHeader()
}

@Preview
@Composable
fun ExpensesItemPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ExpensesItem(
            expenses = Expenses(
                id = 1L,
                title = "Fin de semana",
                amount = 70.0,
                category = ExpensesCategory.PARTY,
                description = "Fin de semana"
            ),
            onExpensesClick = {}
        )
    }
}
