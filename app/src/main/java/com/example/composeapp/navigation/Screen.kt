package com.example.composeapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    data object History : Screen("history", "History", Icons.Default.DateRange)
    data object Home : Screen("home", "Home", Icons.Default.Home)
    data object Profile : Screen("profile", "Profile", Icons.Default.Person)

    data object Detail : Screen("detail")
    data object Booking : Screen("booking")
}