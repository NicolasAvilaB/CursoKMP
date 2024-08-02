package com.cursokmp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import theme.getColorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetStatusBarColor()
            App()
        }
    }
}

@Composable
private fun SetStatusBarColor(){
    val colors = getColorsTheme()
    val systemUiController = rememberSystemUiController()
    val isDarkMode = isSystemInDarkTheme()
    DisposableEffect(systemUiController,isDarkMode){
        systemUiController.setStatusBarColor(
            color = colors.background,
            darkIcons = !isDarkMode
        )
        onDispose {  }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    App()
}