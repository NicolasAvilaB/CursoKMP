package ui.editexpensesscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import navigation.NavGo
import presentation.expensescreen.ExpensesViewModel
import theme.model.DarkModeColors
import ui.components.EditExpenseContentScreen
import ui.components.expensebottomsheet.CategoryBottomSheetContent
import ui.expensescreen.model.Expenses
import ui.expensescreen.model.ExpensesCategory

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditExpensesScreen(
    viewModel: ExpensesViewModel,
    expensesEdit: Expenses?,
    categoryList: List<ExpensesCategory>,
    colors: DarkModeColors,
    navGo: NavGo
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = colors.background,
                title = {
                    androidx.compose.material.Text(
                        text = "Edit Expenses",
                        fontSize = 25.sp,
                        color = colors.textColor
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navGo.popBackStack.invoke()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.padding(10.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = colors.textColor
                        )
                    }
                },
                elevation = 0.dp,
            )
        },
    ) {
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
            EditExpenseContentScreen(
                price = price,
                colors = colors,
                keyboardController = keyboardController,
                scope = scope,
                sheetState = sheetState,
                viewModel = viewModel,
                expensesEdit = expensesEdit,
                navGo = navGo,
                description = description,
                expenseCategory = expenseCategory,
                categorySelected = categorySelected
            )
        }
    }
}
