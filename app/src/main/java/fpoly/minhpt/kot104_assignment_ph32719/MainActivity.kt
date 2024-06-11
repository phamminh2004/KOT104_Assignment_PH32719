package fpoly.minhpt.kot104_assignment_ph32719


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination ="manchao" ){
        composable("manchao"){
            Welcome() {
                navController.navigate("login")
            }
        }
        composable("login"){
            Login(openSignup = {navController.navigate("signup") },
                openDSSP = {navController.navigate("bottom")})
        }
        composable("signup"){
            SingUp {
                navController.navigate("login")
            }
        }
        composable("DSSP"){
            danhsachSP(
                openCar = {navController.navigate("cart") },
                openChitiet = {navController.navigate("chitietsp")},
            )
        }
//        composable("cart"){
//            Cart{
//                navController.navigate("checkuot")
//            }
//        }
        composable("checkuot"){
            Checkuot {
                navController.navigate("sucset")
            }
        }
        composable("sucset"){
            Sucset {
                navController.navigate("DSSP")
            }
        }
//        composable("chitietsp"){
//            Manchitiet {
//                navController.navigate("cart")
//            }
//        }
        composable("bottom") {
            BottomNavigation ()
        }

    }
}



