package com.app.priteshjetpack.ui.screens.orders

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.priteshjetpack.R
import com.app.priteshjetpack.standardPadding
import com.app.priteshjetpack.ui.theme.componentColor
import com.app.priteshjetpack.ui.widgets.TopAppBar
import com.app.priteshjetpack.utils.getStringRes

@Preview(showBackground = true)
@Composable
fun Orders() {
    Column {
        TopAppBar(
            title =  getStringRes(R.string.orders),
            titleAlignment = Alignment.Center,
            actions = {
                IconButton(onClick = {  }) {
                    Icon(Icons.Rounded.Refresh, contentDescription = null)
                }
            },
            elevation = 0.dp,
        )
        Tabs()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Tabs() {
    var tabIndex by remember { mutableStateOf(0) }
    val pastOrders = getStringRes(R.string.past_orders)
    val wishlist  = getStringRes(R.string.wishlist)

    val tabData = listOf(pastOrders,wishlist)
    TabRow(
        selectedTabIndex = tabIndex,
        backgroundColor = MaterialTheme.colors.componentColor
    ) {
        tabData.forEachIndexed { index, text ->
            val isSelected = index == tabIndex
            Tab(
                selected = isSelected,
                onClick = { tabIndex = index }) {
                Text(
                    text = text,
                    modifier = Modifier.padding(standardPadding),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                )
            }
        }
    }
}
