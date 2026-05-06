package com.example.composeapp.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composeapp.presentation.HistoryScreen
import com.example.composeapp.presentation.HomeScreen
import com.example.composeapp.presentation.MovieDetailScreen
import com.example.composeapp.presentation.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        // Halaman Utama
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, modifier = Modifier)
        }

        composable(Screen.History.route) {
            HistoryScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        // Halaman Detail dengan Argument (Seperti pola CatSocial)
        composable(
            route = Screen.Detail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(movieId = movieId, navController = navController)
        }
    }
}