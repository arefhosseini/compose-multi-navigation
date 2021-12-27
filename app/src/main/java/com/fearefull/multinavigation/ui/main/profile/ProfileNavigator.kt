package com.fearefull.multinavigation.ui.main.profile

import com.fearefull.multinavigation.base.ViewEvent
import com.fearefull.multinavigation.base.ViewSideEffect
import com.fearefull.multinavigation.base.ViewState
import com.fearefull.multinavigation.data.model.local.User

class ProfileNavigator {
    sealed class Event : ViewEvent {
        object OnSignOutRequested : Event()
    }

    data class State(
        val user: User? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToAuth : Navigation()
        }
    }
}