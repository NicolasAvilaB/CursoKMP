package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.getColorsTheme
import ui.components.AllExpensesHeader
import ui.components.ExpensedTotalHeader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesScreen(

) {
    val colors = getColorsTheme()

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Column(modifier = Modifier.background(colors.background)) {
                ExpensedTotalHeader(1028.2)
                AllExpensesHeader()
            }
        }
        items(emptyList<String>()){

        }
    }
}

