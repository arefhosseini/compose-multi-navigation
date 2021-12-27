package com.fearefull.multinavigation.ui.auth

import androidx.compose.runtime.Composable

@Composable
fun AuthScreen() {
    val authState = rememberAuthState()
    AuthNavigation(authState = authState)
}