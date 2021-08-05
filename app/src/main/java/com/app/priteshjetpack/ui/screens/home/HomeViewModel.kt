package com.app.priteshjetpack.ui.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.priteshjetpack.ui.screens.home.state.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _viewState:MutableLiveData<HomeState> = MutableLiveData()

    val viewState:LiveData<HomeState> = _viewState
    var popularItems = mutableStateListOf<Int>()
    private set

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch {
            _viewState.value = HomeState.Loading
            delay(1000L)
            repeat(10){
                popularItems.add(it+1)
            }
            _viewState.value = HomeState.Finished
        }
    }
}
