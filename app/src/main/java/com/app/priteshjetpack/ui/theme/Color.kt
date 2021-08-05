package com.app.priteshjetpack.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val LightBackground = Color(0xFFE5E5E5)

val DarkGray = Color(0xFF383838)

val Colors.componentColor: Color
    get() = if(isLight) Color.White else DarkGray
