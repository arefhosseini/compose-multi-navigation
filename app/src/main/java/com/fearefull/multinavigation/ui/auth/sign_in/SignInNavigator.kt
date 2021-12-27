package com.fearefull.multinavigation.ui.auth.sign_in

import com.fearefull.multinavigation.base.ViewEvent
import com.fearefull.multinavigation.base.ViewSideEffect
import com.fearefull.multinavigation.base.ViewState
import com.fearefull.multinavigation.data.model.local.User

class SignInNavigator {
    sealed class Event : ViewEvent {
        data class SignInRequested(
            val user: User
        ) : Event()

        object ToSignUpRequested : Event()
    }

    data class State(
        val loading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToSignUp : Navigation()
            object ToMain : Navigation()
        }
    }
}