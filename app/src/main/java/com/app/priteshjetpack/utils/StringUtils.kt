package com.app.priteshjetpack.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getStringRes(@StringRes resInt:Int):String = LocalContext.current.getString(resInt)