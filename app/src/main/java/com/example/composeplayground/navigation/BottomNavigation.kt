package com.example.composeplayground.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Default.Home,
            route = "home"
        ),
        BarItem(
            title = "Welcome",
            image = Icons.Default.Weekend,
            route = "home"
        ),
        BarItem(
            title = "Profile",
            image = Icons.Default.Preview,
            route = "profile"
        )
    )
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)



@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()

        // 현재 목적지의 route
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { it ->
            BottomNavigationItem(
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(it.image, "") }
            )
        }
    }
}
