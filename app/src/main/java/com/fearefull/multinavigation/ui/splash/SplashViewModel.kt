package com.fearefull.multinavigation.ui.splash

import androidx.lifecycle.viewModelScope
import com.fearefull.multinavigation.base.BaseViewModel
import com.fearefull.multinavigation.data.local.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel<SplashNavigator.Event, SplashNavigator.State, SplashNavigator.Effect>() {

    init {
        viewModelScope.launch { getUser() }
    }

    override fun setInitialState() = SplashNavigator.State()

    override fun handleEvents(event: SplashNavigator.Event) {
        TODO("Not yet implemented")
    }

    private fun getUser() {
        appPreferences.user
            ?.let { setEffect { SplashNavigator.Effect.Navigation.ToMain } }
            ?: setEffect { SplashNavigator.Effect.Navigation.ToAuth }
    }
}