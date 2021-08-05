package com.app.priteshjetpack.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.priteshjetpack.data.HomeScreen
import com.app.priteshjetpack.data.bottomMenuItems
import com.app.priteshjetpack.ui.theme.componentColor
import com.app.priteshjetpack.utils.getStringRes

@Composable
fun AppBottomBar(
    cutOutShape: Shape?,
    navController: NavController,
    bottomMenuItems:List<HomeScreen>,
    onClick: (HomeScreen) -> Unit
) {
    BottomAppBar(
        contentPadding = PaddingValues(0.dp),
        cutoutShape = cutOutShape
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavigation(backgroundColor = MaterialTheme.colors.componentColor) {
            bottomMenuItems.forEach {
                BottomMenuItem(
                    it,
                    currentRoute == it.route,
                    onClick)
            }
        }
    }
}

@Composable
fun RowScope.BottomMenuItem(
    screen: HomeScreen,
    isSelected:Boolean,
    click: (HomeScreen) -> Unit) {

    if(screen is HomeScreen.Placeholder){
        CutShapePlaceholder()
    }else{
        val label = getStringRes(screen.label)
        val iconTint = if(isSelected) MaterialTheme.colors.primary else LocalContentColor.current
        val textColor = if(isSelected) MaterialTheme.colors.primary else LocalContentColor.current
        BottomNavigationItem(
            icon = { Icon(
                imageVector = screen.iconAsset,
                contentDescription = "",
                tint = iconTint
            )},
            selected =isSelected,
            onClick = { click.invoke(screen)},
            label = { Text(
                text = label,
                color = textColor,
                fontSize = 9.sp,
                maxLines = 1
            )},
            alwaysShowLabel = isSelected
        )
    }

}

@Composable
private fun RowScope.CutShapePlaceholder(){
    BottomNavigationItem(icon = {},selected =false, onClick = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppBottomBar() {
    val navController = rememberNavController()
    AppBottomBar(
        cutOutShape = CircleShape,
        navController,
        bottomMenuItems,
        onClick = {})
}