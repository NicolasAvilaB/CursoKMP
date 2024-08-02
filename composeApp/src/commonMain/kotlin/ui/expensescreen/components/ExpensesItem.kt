package ui.expensescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.getColorsTheme
import ui.expensescreen.model.Expenses

@Composable
fun ExpensesItem(
    expenses: Expenses,
    onExpensesClick: (expenses: Expenses) -> Unit
) {
    val colors = getColorsTheme()

    Card(
        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
            .clickable { onExpensesClick(expenses) },
        colors = CardDefaults.cardColors(containerColor = colors.colorExpenseItem),
        shape = RoundedCornerShape(30),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(35),
                color = colors.purple
            ) {
                Image(
                    modifier = Modifier.padding(8.dp),
                    imageVector = expenses.icon,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Imagen Icono Expense Item"
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = expenses.category.name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = colors.textColor
                )
                Text(
                    text = expenses.description,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = "${expenses.amount}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colors.textColor
            )
        }
    }
}