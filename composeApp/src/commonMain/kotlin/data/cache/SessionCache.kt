package data.cache

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

object SessionCache{
    var configDevice: ConfigDevice? = null

    @Composable
    fun isDarkMode(): Boolean{
        return configDevice?.isDarkModeEnabled() ?: isSystemInDarkTheme()
    }
}