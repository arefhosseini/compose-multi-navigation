package com.fearefull.multinavigation.ui.splash

import com.fearefull.multinavigation.base.ViewEvent
import com.fearefull.multinavigation.base.ViewSideEffect
import com.fearefull.multinavigation.base.ViewState

class SplashNavigator {
    sealed class Event : ViewEvent {

    }

    data class State(
        val loading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToAuth : Navigation()
            object ToMain : Navigation()
        }
    }
}