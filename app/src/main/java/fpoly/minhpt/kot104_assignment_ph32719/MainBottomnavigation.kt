package fpoly.minhpt.kot104_assignment_ph32719


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BookmarkBorder

import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Bookmark

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun BottomNavigation(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MyBottomAppBar()
    }
}

@Composable
fun MyBottomAppBar() {
    val context = LocalContext.current.applicationContext
    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold (
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.shadow(8.dp, shape = RoundedCornerShape(10.dp)),
                containerColor = Color(0xFFFFFFFF)
            ) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navigationController.navigate("danhsachsp"){
                            popUpTo(0)

                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon( if (selected.value == Icons.Default.Home)  Icons.Default.Home  else  Icons.Outlined.Home,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )

                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Rounded.Bookmark
                        navigationController.navigate("Cart"){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(if (selected.value == Icons.Rounded.Bookmark) Icons.Rounded.Bookmark  else  Icons.Outlined.BookmarkBorder,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )

                }


                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Notifications
                        navigationController.navigate("checkuot"){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(if (selected.value == Icons.Default.Notifications) Icons.Default.Notifications else Icons.Outlined.Notifications,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )

                }



                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Person
                        navigationController.navigate("Sucset"){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(if (selected.value == Icons.Default.Person) Icons.Default.Person else Icons.Outlined.Person,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )

                }







            }
        }
    )
    {paddingValues ->
        NavHost(navController = navigationController,
            startDestination = "danhsachsp",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("danhsachsp"){ danhsachSP(
                openCar = {navigationController.navigate("Cart")},
                openChitiet = { productId -> navigationController.navigate("chitietsp/$productId")},
            )}
            composable("Cart"){ Cart {
                navigationController.navigate("checkuot")
            } }
            composable("checkuot"){ Checkuot {

            } }
            composable("Sucset"){ Sucset {

            }
            }
            composable(
                route = "chitietsp/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: ""
                Manchitiet(
                    openCart = { navigationController.navigate("Cart") },
                    productId = productId
                )
            }


        }

    }

}