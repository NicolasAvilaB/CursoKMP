package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.getColorsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesDescription(
    descContent: String,
    onDescriptionChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
) {
    val text = remember { mutableStateOf(descContent) }
    val colors = getColorsTheme()
    Column {
        Text(
            text = "Description",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(containerColor = colors.background),
            value = text.value,
            onValueChange = { newText ->
                if (newText.length <= 100) {
                    text.value = newText
                    onDescriptionChange(newText)
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                color = colors.textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.Black
        )
    }
}