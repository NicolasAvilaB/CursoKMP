package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import navigation.NavGo
import presentation.expensescreen.ExpensesViewModel
import theme.model.DarkModeColors
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditExpenseContentScreen(
    price: MutableState<Double>,
    description: MutableState<String>,
    expenseCategory: MutableState<String>,
    categorySelected: MutableState<String>,
    colors: DarkModeColors,
    keyboardController: SoftwareKeyboardController?,
    scope: CoroutineScope,
    viewModel: ExpensesViewModel,
    expensesEdit: Expenses?,
    navGo: NavGo,
    sheetState: ModalBottomSheetState
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        ExpenseAmount(
            priceContent = price.value,
            colors = colors,
            keyboardController = keyboardController,
            onPriceChange = { newPrice ->
                price.value = newPrice
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExpenseTypeSelector(
            categorySelected = categorySelected.value,
            colors = colors,
            openBottomSheet = {
                scope.launch {
                    sheetState.show()
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExpensesDescription(
            descContent = description.value,
            onDescriptionChange = { newDesc ->
                description.value = newDesc
            },
            keyboardController = keyboardController
        )
        Button(
            modifier = Modifier.fillMaxWidth().height(70.dp).clip(
                RoundedCornerShape(45)
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colors.purple,
                contentColor = Color.White
            ),
            onClick = {
                val expense = Expenses(
                    amount = price.value,
                    category = ExpensesCategory.valueOf(expenseCategory.value),
                    description = description.value,
                    title = description.value
                )
                val expenseFromEdit = expensesEdit?.id.let { expense.copy(id = it ?:0 ) }
                viewModel.editExpense(expenseFromEdit)
                navGo.popBackStack.invoke()
            }
        ) {
            Text(
                text = "Guardar",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}