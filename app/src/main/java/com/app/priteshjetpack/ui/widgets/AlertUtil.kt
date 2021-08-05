package com.app.priteshjetpack.ui.widgets

import androidx.compose.runtime.compositionLocalOf

val showAlert :(()->Unit) ? = null // callback
val LocalAlert = compositionLocalOf { showAlert }