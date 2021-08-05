package com.app.priteshjetpack.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.priteshjetpack.R

sealed class HomeScreen(
    val route:String ,
    val iconAsset: ImageVector,
    @StringRes val label: Int,
) {
object Home:HomeScreen("Home", Icons.Filled.Home, R.string.home)
object Orders:HomeScreen("Orders", Icons.Filled.List,R.string.orders)
object Notifications:HomeScreen("Notifications", Icons.Filled.Notifications,R.string.notifications)
object User:HomeScreen("User", Icons.Filled.AccountCircle,R.string.user)
object Placeholder:HomeScreen("", Icons.Filled.Build,0)
}

val bottomMenuItems = listOf(
    HomeScreen.Home,HomeScreen.Orders,HomeScreen.Placeholder,HomeScreen.Notifications,HomeScreen.User
)