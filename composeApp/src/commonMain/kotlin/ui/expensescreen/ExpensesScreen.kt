package ui.expensescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.NavGo
import presentation.expensescreen.events.ExpensesUiState
import theme.model.DarkModeColors
import ui.expensescreen.components.AllExpensesHeader
import ui.expensescreen.components.ExpensedTotalHeader
import ui.expensescreen.components.ExpensesItem
import ui.expensescreen.model.Expenses

@Composable
fun ExpensesScreen(
    uiState: ExpensesUiState,
    navGo: NavGo,
    colors: DarkModeColors,
    onExpensesClick: (expenses: Expenses) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = colors.background,
                title = {
                    Text(
                        text = "DashBoard",
                        fontSize = 25.sp,
                        color = colors.textColor
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Filled.Apps,
                        contentDescription = "Menu",
                        tint = colors.textColor
                    )
                },
                elevation = 0.dp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navGo.addExpenses.invoke()
                },
                shape = RoundedCornerShape(50),
                backgroundColor = colors.addIconColor,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = "Add Expenses"
                )
            }
        }
    ) {
        ExpensesContent(
            uiState = uiState,
            colors = colors,
            onExpensesClick = onExpensesClick
        )
    }
}
    

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesContent(
    uiState: ExpensesUiState,
    colors: DarkModeColors,
    onExpensesClick: (expenses: Expenses) -> Unit
){
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

