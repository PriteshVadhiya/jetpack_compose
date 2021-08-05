package com.app.priteshjetpack.ui.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.priteshjetpack.standardPadding

@Composable
fun DefaultSpacer() = Spacer(modifier = Modifier.padding(standardPadding))

@Composable
fun TitleSpacer() = Spacer(modifier = Modifier.padding(standardPadding/2))