package com.app.priteshjetpack.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.priteshjetpack.R
import com.app.priteshjetpack.ui.theme.componentColor
import com.app.priteshjetpack.utils.getStringRes

@Composable
fun TopAppBar(
    title:String,
    titleAlignment: Alignment = Alignment.CenterStart,
    elevation:Dp = AppBarDefaults.TopAppBarElevation,
    actions: @Composable RowScope.() -> Unit = {}
){
    TopAppBar (
        modifier = Modifier.height(35.dp),
        title = { Box(modifier = Modifier.fillMaxSize()){
            Text(text = title, modifier = Modifier.align(titleAlignment),)
        } },
        actions =actions,
        elevation = elevation,
        backgroundColor = MaterialTheme.colors.componentColor
    )
}

@Preview
@Composable
fun PreviewAppTopBar(){
    TopAppBar(title = getStringRes(resInt = R.string.home_title))
}