package fpoly.minhpt.kot104_assignment_ph32719.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fpoly.minhpt.kot104_assignment_ph32719.Screens
import fpoly.minhpt.kot104_assignment_ph32719.ui.theme.KOT104_Assignment_PH32719Theme


@Composable
fun HomeScreen(navControl: NavHostController) {
    val context = LocalContext.current.applicationContext
    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.shadow(8.dp, shape = RoundedCornerShape(10.dp)),
                containerColor = Color(0xFFFFFFFF)
            ) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navigationController.navigate(Screens.Home.screen) {
                            popUpTo(0)

                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(
                        if (selected.value == Icons.Default.Home) Icons.Default.Home else Icons.Outlined.Home,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )

                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Favorite
                        navigationController.navigate(Screens.BookMark.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(
                        if (selected.value == Icons.Default.Favorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )

                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Notifications
                        navigationController.navigate(Screens.Notification.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(
                        if (selected.value == Icons.Default.Notifications) Icons.Default.Notifications else Icons.Outlined.Notifications,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )

                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Person
                        navigationController.navigate(Screens.Profile.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(
                        if (selected.value == Icons.Default.Person) Icons.Default.Person else Icons.Outlined.Person,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    )
    { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screen) { Home(navControl) }
            composable(Screens.BookMark.screen) { Favorite() }
            composable(Screens.Notification.screen) { Notification() }
            composable(Screens.Profile.screen) { Profile() }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    KOT104_Assignment_PH32719Theme {
        val navControl = rememberNavController()
        HomeScreen(navControl)
    }
}