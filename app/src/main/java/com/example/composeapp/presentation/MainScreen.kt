package com.example.composeapp.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import com.example.composeapp.component.bottomNav.BottomNavComponent
import com.example.composeapp.component.TopBarHomeScreen
import com.example.composeapp.component.bottomNav.AnimatedBottomNav
//import com.example.composeapp.component.bottomNav.CustomBottomNav
import com.example.composeapp.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // NavHost global untuk mengatur perpindahan ke DetailScreen
    NavHost(navController = navController, startDestination = "main_pager") {

        // Rute 1: Konten utama dengan Pager dan BottomNav
        composable("main_pager") {
            MainPagerContent(navController = navController)
        }

        // Rute 2: DetailScreen (Diluar Pager)
        composable(
            route = Screen.Detail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(movieId = movieId, navController = navController)
        }
    }
}
@Composable
fun MainPagerContent(navController: NavHostController) {
    val navigationItems = listOf(Screen.History, Screen.Home, Screen.Profile)

    // Mengatur agar selalu mulai dari indeks 1 (Home)
    val pagerState = rememberPagerState(initialPage = 1) {
        navigationItems.size
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            if (pagerState.currentPage == 1) {
                TopBarHomeScreen(
                    name = "Daffa Yusa", //
                    navController = navController
                )
            }
        },
        bottomBar = {
            AnimatedBottomNav(
                pagerState = pagerState,
                navigationItems = navigationItems,
                onPageSelected = { index ->
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            // beyondViewportPageCount membantu preload halaman tetangga agar transisi lancar
            beyondViewportPageCount = 1
        ) { page ->
            when (navigationItems[page]) {
                Screen.History -> HistoryScreen()
                Screen.Home -> HomeScreen(navController = navController, modifier = Modifier)
                Screen.Profile -> ProfileScreen()
                else -> Unit
            }
        }
    }
}