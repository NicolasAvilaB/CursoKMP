package ui.expenseseditscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import navigation.NavGo
import theme.model.DarkModeColors
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ExpensesEditScreen(
    expensesEdit: Expenses?,
    categoryList: List<ExpensesCategory>,
    addExpenses: (Expenses) -> Unit,
    colors: DarkModeColors,
    navGo: NavGo,
) {
    val price = remember { mutableStateOf(expensesEdit?.amount ?: 0.0) }
    val description = remember { mutableStateOf(expensesEdit?.description ?: "") }
    val expenseCategory = remember { mutableStateOf(expensesEdit?.category?.name ?: "") }
    val categorySelected =
        remember { mutableStateOf(expensesEdit?.category?.name ?: "Select to Categpry") }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue) {
        if (sheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyboardController?.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            CategoryBottomSheetContent(
                categories = categoryList,
                onCategorySelected = { category ->
                    categorySelected.value = category.name
                    expenseCategory.value = category.name
                    scope.launch {
                        sheetState.hide()
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {

        }
    }

}

@Composable
fun ExpenseAmount(
    priceContent: Double,
    colors: DarkModeColors,
    keyboardController: SoftwareKeyboardController?,
    onPriceChange: (Double) -> Unit,
) {
    val text = remember { mutableStateOf("$priceContent") }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Amount",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.value,
                fontSize = 25.sp,
                color = colors.textColor,
                fontWeight = FontWeight.ExtraBold
            )

            TextField(
                value = text.value,
                onValueChange = { newText ->
                    text.value = newText
                    onPriceChange(newText.toDoubleOrNull() ?: 0.0)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
            Text(
                text = "USD",
                fontSize = 20.sp,
                color = Color.Gray,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
    }
}

@Composable
private fun CategoryBottomSheetContent(
    categories: List<ExpensesCategory>,
    onCategorySelected: (ExpensesCategory) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                onCategorySelected = onCategorySelected
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: ExpensesCategory,
    onCategorySelected: (ExpensesCategory) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
            onCategorySelected(category)
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(40.dp).clip(CircleShape),
            imageVector = category.icon,
            contentDescription = "category icon",
            contentScale = ContentScale.Crop
        )
        Text(text = category.name)
    }
}