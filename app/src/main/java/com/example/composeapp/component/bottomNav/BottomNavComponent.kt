package com.example.composeapp.component.bottomNav


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.component.TopBarHomeScreen
import com.example.composeapp.navigation.Screen
import com.example.composeapp.ui.theme.ComposeAppTheme

//@Composable
//fun BottomNavComponent(
//    navController: NavHostController,
//    modifier: Modifier = Modifier
//) {
//    val navigationItems = listOf(
//        Screen.Home,
//        Screen.History,
//        Screen.Profile
//    )
//
//    NavigationBar(
//        modifier = modifier,
//
//        containerColor = MaterialTheme.colorScheme.surface,
//        contentColor = MaterialTheme.colorScheme.onSurface,
//        tonalElevation = 8.dp
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentDestination = navBackStackEntry?.destination
//
//        navigationItems.forEach { item ->
//            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
//
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = item.icon,
//                        contentDescription = item.title
//                    )
//                },
//                label = { Text(item.title) },
//                selected = isSelected,
//                onClick = {
//                    navController.navigate(item.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
//                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            )
//        }
//    }
//}
//
//@Composable
//fun CustomBottomNav(
//    navController: NavHostController,
//    modifier: Modifier = Modifier
//) {
//    val navigationItems = listOf(Screen.History, Screen.Home, Screen.Profile)
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    val selectedIndex = navigationItems.indexOfFirst { it.route == currentRoute }.coerceAtLeast(0)
//
//    val configuration = LocalConfiguration.current
//    val screenWidth = configuration.screenWidthDp.dp
//    val density = LocalDensity.current
//
//    val tabWidth = with(density) { (screenWidth - 32.dp).toPx() / navigationItems.size }
//    val animatedOffset by animateFloatAsState(
//        targetValue = (tabWidth * selectedIndex) + (tabWidth / 2),
//        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
//    )
//
//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .height(70.dp)
//            .graphicsLayer {
//                shape = BottomNavShape(
//                    offset = animatedOffset,
//                    circleRadius = 80f // Sesuaikan kedalaman lekukan
//                )
//                clip = true
//            }
//            .background(Color.White),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            navigationItems.forEachIndexed { index, item ->
//                val isSelected = selectedIndex == index
//
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .clickable(
//                            interactionSource = remember { MutableInteractionSource() },
//                            indication = null
//                        ) {
//                            navController.navigate(item.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//
//                        modifier = Modifier.offset(y = if (isSelected) (-25).dp else 0.dp)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .size(if (isSelected) 50.dp else 24.dp)
//                                .background(
//                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
//                                    shape = CircleShape
//                                ),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Icon(
//                                imageVector = item.icon,
//                                contentDescription = item.title,
//                                tint = if (isSelected) Color.White else Color.Gray,
//                                modifier = Modifier.size(if (isSelected) 28.dp else 24.dp)
//                            )
//                        }
//                        if (!isSelected) {
//                            Text(text = item.title, fontSize = 10.sp, color = Color.Gray)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MovieFloatingNavBar(
//    navController: NavHostController,
//    isVisible: Boolean // State untuk mengontrol muncul/sembunyi
//) {
//    val screens = listOf(
//        Screen.Home,
//        Screen.Profile,
//        Screen.Profile
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    AnimatedVisibility(
//        visible = isVisible,
//        enter = slideInVertically(initialOffsetY = { it }),
//        exit = slideOutVertically(targetOffsetY = { it }),
//    ) {
//        Surface(
//            modifier = Modifier
//                .padding(horizontal = 24.dp, vertical = 24.dp)
//                .fillMaxWidth(),
//            shape = RoundedCornerShape(20.dp),
//            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f),
//            tonalElevation = 10.dp,
//            shadowElevation = 10.dp
//        ) {
//            NavigationBar(
//                containerColor = Color.Transparent,
//                modifier = Modifier.height(64.dp)
//            ) {
//                screens.forEach { screen ->
//                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
//
//                    NavigationBarItem(
//                        selected = selected,
//                        onClick = {
//                            navController.navigate(screen.route) {
//                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        },
//                        icon = {
//                            Icon(
//                                imageVector = screen.icon,
//                                contentDescription = screen.title,
//                                tint = if (selected) MaterialTheme.colorScheme.primary
//                                else MaterialTheme.colorScheme.onSurfaceVariant
//                            )
//                        },
//                        // Kita hilangkan label agar lebih minimalis (Floating Style)
//                        alwaysShowLabel = false,
//                        colors = NavigationBarItemDefaults.colors(
//                            indicatorColor = Color.Transparent // Menghilangkan background bulat bawaan M3
//                        )
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun AnimatedBottomNav(
    pagerState: PagerState,
    navigationItems: List<Screen>,
    onPageSelected: (Int) -> Unit
) {

    val selectedItem = pagerState.currentPage

    val barColor = MaterialTheme.colorScheme.primaryContainer
    val circleColor = MaterialTheme.colorScheme.primary
    val selectedColor = MaterialTheme.colorScheme.onPrimary
    val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant

    val circleRadius = 26.dp
    var barSize by remember { mutableStateOf(IntSize(0, 0)) }

    val offsetStep = remember(barSize) {
        barSize.width.toFloat() / (navigationItems.size * 2)
    }
    val offset = remember(selectedItem, offsetStep) {
        offsetStep + selectedItem * 2 * offsetStep
    }

    val circleRadiusPx = LocalDensity.current.run { circleRadius.toPx().toInt() }
    val offsetTransition = updateTransition(offset, "offset transition")

    val animation = spring<Float>(dampingRatio = 0.6f, stiffness = Spring.StiffnessLow)

    val cutoutOffset by offsetTransition.animateFloat(
        transitionSpec = { if (initialState == 0f) snap() else animation },
        label = "cutout offset"
    ) { it }

    val circleOffset by offsetTransition.animateIntOffset(
        transitionSpec = { if (initialState == 0f) snap() else spring(0.6f, Spring.StiffnessLow) },
        label = "circle offset"
    ) {
        IntOffset(it.toInt() - circleRadiusPx, -circleRadiusPx)
    }

    var barShape = remember(cutoutOffset) {
        BarShape(
            offset = cutoutOffset,
            circleRadius = circleRadius,
            cornerRadius = 20.dp,
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 32.dp, top = 16.dp)
    ) {
        // Bola yang melompat
        Circle(
            modifier = Modifier
                .offset { circleOffset }
                .zIndex(1f),
            color = circleColor,
            radius = circleRadius,
            icon = navigationItems[selectedItem].icon ,
            iconColor = selectedColor,
            title = navigationItems[selectedItem].title
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .onPlaced { barSize = it.size }
                .graphicsLayer {
                    shape = barShape
                    clip = true
                },
            shape = RoundedCornerShape(24.dp),
            color = barColor,
            tonalElevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationItems.forEachIndexed { index, screen ->
                    val isSelected = index == selectedItem

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) {
                                onPageSelected(index)
                            }
                        },
                        icon = {
                            val iconAlpha by animateFloatAsState(
                                targetValue = if (isSelected) 0f else 1f,
                                label = "Navbar item icon"
                            )
                            screen.icon?.let { imgVector ->
                                Icon(
                                    imageVector = imgVector,
                                    contentDescription = screen.title,
                                    modifier = Modifier.alpha(iconAlpha)
                                )
                            }
                        },
                        label = {
                            // Perbaikan 3: Cek apakah title ada sebelum merender Text
                            if (!isSelected) {
                                screen.title?.let { labelText ->
                                    Text(labelText, fontSize = 10.sp)
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            selectedTextColor = Color.Transparent,
                            unselectedIconColor = unselectedColor,
                            unselectedTextColor = unselectedColor,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun BottomBarPrev() {
    val navController = rememberNavController()
    ComposeAppTheme {
//        AnimatedBottomNav(
//            navController
//        )
    }

}