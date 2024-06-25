import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.NavController
import navigation.NavGo
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.AppTheme
import theme.getColorsTheme

@Composable
@Preview
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()
        AppTheme {
            val navigator = rememberNavigator()
            val navGo = remember(navigator) { NavGo(navigator) }
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        backgroundColor = colors.background,
                        title = {
                            Text(
                                text = "DashBoard",
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
                                    imageVector = Icons.Filled.Apps,
                                    contentDescription = "Menu",
                                    tint = colors.textColor
                                )
                            }
                        },
                        elevation = 0.dp,
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            navGo.navAddExpenses.invoke()
                        },
                        shape = RoundedCornerShape(50),
                        backgroundColor = colors.addIconColor,
                        contentColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            tint = Color.White,
                            contentDescription = "Add Expenses"
                        )
                    }
                }
            ) {
                NavController(
                    navigator = navigator,
                    navGo = navGo
                )
            }
        }
    }
}
