package data

import data.cache.ConfigDevice
import platform.UIKit.UIScreen
import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle

class ConfigDevice: ConfigDevice {
    override fun isDarkModeEnabled(): Boolean {
        val osTheme: UITraitCollection = UIScreen.mainScreen.traitCollection
        return osTheme.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    }
}