package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.model.DarkModeColors

@OptIn(ExperimentalMaterial3Api::class)
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
                colors = TextFieldDefaults.textFieldColors(containerColor = colors.background),
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
                    color = colors.textColor,
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
        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp
        )
    }
}