package com.app.priteshjetpack.ui.screens.user

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.app.priteshjetpack.R
import com.app.priteshjetpack.ui.widgets.TopAppBar
import com.app.priteshjetpack.utils.getStringRes

@Preview(showBackground = true)
@Composable
fun User() {
    Column {
        TopAppBar(
            title =  getStringRes(R.string.user),
            titleAlignment = Alignment.Center
        )
        // TODO: Add other view of User
    }
}