package com.example.movieui.core.route

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieui.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            name = "Home",
            route = AppRouteName.Home,
            icon = R.drawable.ic_home // Pastikan Anda memiliki ikon yang sesuai di folder res/drawable
        ),
        BottomNavItem(
            name = "Snack",
            route = AppRouteName.Snack,
            icon = R.drawable.ic_snack // Pastikan Anda memiliki ikon yang sesuai di folder res/drawable
        ),
        BottomNavItem(
            name = "Profile",
            route = AppRouteName.Profile,
            icon = R.drawable.ic_profile // Pastikan Anda memiliki ikon yang sesuai di folder res/drawable
        )
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.name
                    )
                },
                label = { Text(text = item.name) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Int
)
