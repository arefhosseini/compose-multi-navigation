package com.fearefull.multinavigation.ui.auth.sign_in

import androidx.lifecycle.viewModelScope
import com.fearefull.multinavigation.base.BaseViewModel
import com.fearefull.multinavigation.data.local.AppPreferences
import com.fearefull.multinavigation.data.model.local.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel<SignInNavigator.Event, SignInNavigator.State, SignInNavigator.Effect>() {

    override fun setInitialState() = SignInNavigator.State()

    override fun handleEvents(event: SignInNavigator.Event) {
        when(event) {
            is SignInNavigator.Event.SignInRequested -> {
                viewModelScope.launch { doSignIn(event.user) }
            }
            is SignInNavigator.Event.ToSignUpRequested -> {
                setEffect { SignInNavigator.Effect.Navigation.ToSignUp }
            }
        }
    }

    private fun doSignIn(user: User) {
        appPreferences.user = user
        setEffect { SignInNavigator.Effect.Navigation.ToMain }
    }
}