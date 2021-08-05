package com.app.priteshjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.navigation.compose.rememberNavController
import com.app.priteshjetpack.data.HomeScreen
import com.app.priteshjetpack.data.bottomMenuItems
import com.app.priteshjetpack.ui.navigation.HomeNavGraph
import com.app.priteshjetpack.ui.theme.PriteshJetpackTheme
import com.app.priteshjetpack.ui.widgets.AppBottomBar
import com.app.priteshjetpack.ui.widgets.LocalAlert
import com.app.priteshjetpack.utils.getStringRes
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun App() {
    PriteshJetpackTheme {
        val fabShape = CircleShape
        val navController = rememberNavController()

        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        val alertMessage = getStringRes(R.string.alert_message)

        val showAlert :()->Unit = {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(alertMessage)
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                AppBottomBar(fabShape, navController, bottomMenuItems) {
                    navController.navigate(it.route) {
                        restoreState = true
                        launchSingleTop = true
                        popUpTo(HomeScreen.Home.route)
                    }
                }
            },
            floatingActionButton = {
                FAB(fabShape = fabShape)
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        ) {
            CompositionLocalProvider(LocalAlert provides showAlert) {
                HomeNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun FAB(fabShape: Shape) {
    FloatingActionButton(
        shape = fabShape,
        onClick = {  },
        backgroundColor = MaterialTheme.colors.primary,
        content = { Icon(Icons.Filled.Favorite, "", tint = Color.White) }
    )
}
