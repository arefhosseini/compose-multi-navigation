package com.fearefull.multinavigation.ui.main.profile

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fearefull.multinavigation.base.BaseViewModel
import com.fearefull.multinavigation.data.local.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel<ProfileNavigator.Event, ProfileNavigator.State, ProfileNavigator.Effect>() {

    init {
        viewModelScope.launch { getUser() }
    }

    override fun setInitialState() = ProfileNavigator.State()

    override fun handleEvents(event: ProfileNavigator.Event) {
        when(event) {
            is ProfileNavigator.Event.OnSignOutRequested -> {
                viewModelScope.launch { doSignOut() }
            }
        }
    }

    private fun getUser() {
        val user = appPreferences.user
        setState {
            copy(user = user)
        }
    }

    private fun doSignOut() {
        appPreferences.user = null
        setEffect { ProfileNavigator.Effect.Navigation.ToAuth }
    }
}