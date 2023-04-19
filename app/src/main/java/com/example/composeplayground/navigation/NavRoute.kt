//package com.example.composeplayground.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//sealed class NavRoute(val route: String) {
//    object Home : NavRoute("home")
//    object Welcome : NavRoute("welcome")
//    object Profile : NavRoute("profile")
//}
//
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = NavRoute.Home.route ){
//        composable(NavRoute.Home.route + "/{username}"){navBackStackEntry ->
//           val text : String =  navBackStackEntry.arguments?.getString("username") ?: ""
//            HomeScreen(text)
//        }
//        composable(NavRoute.Profile.route ){
//            navController.navigate(NavRoute.Home.route + "/$username")
//            ProfileScreen()
//        }
//        composable(NavRoute.Welcome.route){
//            WelcomeScreen()
//        }
//    }
//}
