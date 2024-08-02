package ui.expensescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.NavGo
import presentation.expensescreen.ExpensesViewModel
import presentation.expensescreen.events.ExpensesUiState
import theme.model.DarkModeColors
import ui.expensescreen.components.AllExpensesHeader
import ui.expensescreen.components.ExpensedTotalHeader
import ui.expensescreen.components.ExpensesItem
import ui.expensescreen.components.SwipeToDeleteContainer
import ui.expensescreen.model.Expenses

@Composable
fun ExpensesScreen(
    uiState: ExpensesUiState,
    navGo: NavGo,
    viewModel: ExpensesViewModel,
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
            viewModel = viewModel,
            onExpensesClick = onExpensesClick
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesContent(
    uiState: ExpensesUiState,
    colors: DarkModeColors,
    viewModel: ExpensesViewModel,
    onExpensesClick: (expenses: Expenses) -> Unit
) {
    when (uiState) {
        is ExpensesUiState.LoadingUiState -> {
            Text("Loading")
        }

        is ExpensesUiState.DisplayUiState -> {

            val stateValue = uiState
            if (stateValue.expenses.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay mas expensas, agregue algunas por favor",
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.background),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    stickyHeader {
                        Column(modifier = Modifier.background(colors.background).padding(10.dp)) {
                            ExpensedTotalHeader(stateValue.totalExpenses)
                            AllExpensesHeader()
                        }
                    }
                    stateValue.expenses?.let { listItems ->
                        items(listItems.size) { index ->
                            val itemExpense = listItems[index]
                            SwipeToDeleteContainer(
                                item = itemExpense,
                                idItem = itemExpense.id,
                                viewModel = viewModel
                            ) {
                                ExpensesItem(
                                    expenses = itemExpense,
                                    onExpensesClick = onExpensesClick
                                )
                            }
                        }
                    }
                }
            }
        }

        is ExpensesUiState.ErrorUiState -> {
            Text("Error ${uiState.e}")
        }
    }
}

