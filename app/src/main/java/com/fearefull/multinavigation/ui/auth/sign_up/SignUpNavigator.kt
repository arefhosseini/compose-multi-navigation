package com.fearefull.multinavigation.ui.auth.sign_up

import com.fearefull.multinavigation.base.ViewEvent
import com.fearefull.multinavigation.base.ViewSideEffect
import com.fearefull.multinavigation.base.ViewState
import com.fearefull.multinavigation.data.model.local.User

class SignUpNavigator {
    sealed class Event : ViewEvent {
        data class SignUpRequested(
            val user: User
        ) : Event()

        object ToSignInRequested : Event()
    }

    data class State(
        val loading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToSignIn : Navigation()
            object ToMain : Navigation()
        }
    }
}