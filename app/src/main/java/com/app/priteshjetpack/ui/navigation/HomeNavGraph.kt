package com.app.priteshjetpack.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.priteshjetpack.data.HomeScreen
import com.app.priteshjetpack.ui.screens.home.Home
import com.app.priteshjetpack.ui.screens.home.HomeViewModel
import com.app.priteshjetpack.ui.screens.notifications.Notifications
import com.app.priteshjetpack.ui.screens.orders.Orders
import com.app.priteshjetpack.ui.screens.user.User
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeNavGraph(
    navController: NavHostController,
) {
    val homeViewModel by remember {
        mutableStateOf(HomeViewModel())
    }
    NavHost(navController, startDestination = HomeScreen.Home.route) {
        composable(HomeScreen.Home.route) {
            Home(homeViewModel)
        }
        composable(HomeScreen.Orders.route) {
          Orders()
        }
        composable(HomeScreen.Notifications.route) {
            Notifications()
        }
        composable(HomeScreen.User.route) {
            User()
        }
    }
}