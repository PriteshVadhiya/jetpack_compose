package com.app.priteshjetpack.ui.screens.home.state

sealed class HomeState{
    object Loading:HomeState()
    object Finished:HomeState()
}
