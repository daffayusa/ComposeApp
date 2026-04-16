package com.example.composeapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.component.bottomNav.BottomNavComponent
import com.example.composeapp.component.TopBarHomeScreen
import com.example.composeapp.component.bottomNav.AnimatedBottomNav
import com.example.composeapp.component.bottomNav.CustomBottomNav
import com.example.composeapp.navigation.Screen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when(currentDestination){
                Screen.Home.route -> TopBarHomeScreen(
                    name = "Daffa Yusa",
                    navController = navController,
                    modifier = Modifier
                        .shadow(
                            elevation = 12.dp,
                            shape = RectangleShape,
                            clip = false
                        )
                        .zIndex(1f)
                )
            }
        },
        bottomBar = {
            AnimatedBottomNav(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){

            composable(Screen.History.route) { HistoryScreen() }
            composable(Screen.Home.route){HomeScreen()}
            composable(Screen.Profile.route) { ProfileScreen() }
        }

    }
}