package com.fearefull.multinavigation.ui.auth.sign_up

import androidx.lifecycle.viewModelScope
import com.fearefull.multinavigation.base.BaseViewModel
import com.fearefull.multinavigation.data.local.AppPreferences
import com.fearefull.multinavigation.data.model.local.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel<SignUpNavigator.Event, SignUpNavigator.State, SignUpNavigator.Effect>() {

    override fun setInitialState() = SignUpNavigator.State()

    override fun handleEvents(event: SignUpNavigator.Event) {
        when(event) {
            is SignUpNavigator.Event.SignUpRequested -> {
                viewModelScope.launch { doSignUp(event.user) }
            }
            is SignUpNavigator.Event.ToSignInRequested -> {
                setEffect { SignUpNavigator.Effect.Navigation.ToSignIn }
            }
        }
    }

    private fun doSignUp(user: User) {
        appPreferences.user = user
        setEffect { SignUpNavigator.Effect.Navigation.ToMain }
    }
}