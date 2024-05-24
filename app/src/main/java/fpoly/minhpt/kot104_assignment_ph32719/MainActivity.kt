package fpoly.minhpt.kot104_assignment_ph32719

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fpoly.minhpt.kot104_assignment_ph32719.ui.screens.HomeScreen
import fpoly.minhpt.kot104_assignment_ph32719.ui.screens.LoginScreen
import fpoly.minhpt.kot104_assignment_ph32719.ui.screens.ProductDetail
import fpoly.minhpt.kot104_assignment_ph32719.ui.screens.SignupScreen
import fpoly.minhpt.kot104_assignment_ph32719.ui.screens.Welcome
import fpoly.minhpt.kot104_assignment_ph32719.ui.theme.KOT104_Assignment_PH32719Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KOT104_Assignment_PH32719Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navControl = rememberNavController()

                    NavHost(
                        navController = navControl,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            Welcome(navControl)
                        }
                        composable("login") {
                            LoginScreen(navControl)
                        }
                        composable("sign") {
                            SignupScreen(navControl)
                        }
                        composable("home") {
                            HomeScreen(navControl)
                        }
                        composable(Screens.ProductDetail.screen) {
                            ProductDetail()
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        KOT104_Assignment_PH32719Theme {
            val navControl = rememberNavController()
            HomeScreen(navControl)
        }
    }
}