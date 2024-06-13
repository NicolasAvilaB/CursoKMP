package com.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ui.components.ExpensedTotalHeader

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpensedTotalHeader(total = 1028.4)
}