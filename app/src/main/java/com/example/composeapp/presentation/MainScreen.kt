package com.example.composeapp.presentation

import ProfileScreen
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.example.composeapp.component.SimpleTopBar
//import com.example.composeapp.component.bottomNav.BottomNavComponent
import com.example.composeapp.component.TopBarHomeScreen
import com.example.composeapp.component.bottomNav.AnimatedBottomNav
//import com.example.composeapp.component.bottomNav.CustomBottomNav
import com.example.composeapp.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_pager") {

        composable("main_pager") {
            MainPagerContent(navController = navController)
        }

        composable(
            route = Screen.Detail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(movieId = movieId, navController = navController, modifier = Modifier.navigationBarsPadding())
        }
        composable(
            route = Screen.Booking.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") {type = NavType.IntType })
        ){ backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movireId") ?: 0
            TicketBookingScreen(movieId = movieId, navController = navController)

        }
    }
}
@Composable
fun MainPagerContent(navController: NavHostController) {
    val navigationItems = listOf(Screen.History, Screen.Home, Screen.Profile)

    val pagerState = rememberPagerState(initialPage = 1) {
        navigationItems.size
    }

    val scope = rememberCoroutineScope()

    Scaffold(
//        topBar = {
//            AnimatedContent(
//                targetState = pagerState.currentPage,
//                transitionSpec = {
//                    val animDuration = 1
//
//                    if (targetState > initialState) {
//                        (slideInHorizontally(animationSpec = tween(animDuration)) { width -> width } +
//                                fadeIn(animationSpec = tween(animDuration))).togetherWith(
//                            slideOutHorizontally(animationSpec = tween(animDuration)) { width -> -width } +
//                                    fadeOut(animationSpec = tween(animDuration))
//                        )
//                    } else {
//                        (slideInHorizontally(animationSpec = tween(animDuration)) { width -> -width } +
//                                fadeIn(animationSpec = tween(animDuration))).togetherWith(
//                            slideOutHorizontally(animationSpec = tween(animDuration)) { width -> width } +
//                                    fadeOut(animationSpec = tween(animDuration))
//                        )
//                    }.using(SizeTransform(clip = false))
//                },
//                label = "TopBarAnimation"
//            ) { targetPage ->
//                when (targetPage) {
//                    0 -> SimpleTopBar(
//                        title = "History",
//                        onBackClick = { scope.launch { pagerState.animateScrollToPage(1) } }
//                    )
//                    1 -> TopBarHomeScreen(
//                        name = "Daffa Yusa",
//                        navController = navController
//                    )
//                    2 -> SimpleTopBar(
//                        title = "Profile",
//                        onBackClick = { scope.launch { pagerState.animateScrollToPage(1) } }
//                    )
//                }
//            }
//        },
        bottomBar = {
            AnimatedBottomNav(
                pagerState = pagerState,
                navigationItems = navigationItems,
                modifier = Modifier.navigationBarsPadding(),
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
//            modifier = Modifier.padding(innerPadding),
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            beyondViewportPageCount = 1
        ) { page ->
            when (navigationItems[page]) {
                Screen.History -> HistoryScreen()
                Screen.Home -> HomeScreen(navController = navController, modifier = Modifier)
                Screen.Profile -> ProfileScreen(
                    modifier = Modifier,
                    onNavigateHome = {
                    scope.launch { pagerState.animateScrollToPage(1) } // Geser ke Home
                })
                else -> Unit
            }
        }
    }
}