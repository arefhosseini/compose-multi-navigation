package com.fearefull.multinavigation.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fearefull.multinavigation.ui.auth.sign_in.SignInScreen
import com.fearefull.multinavigation.ui.auth.sign_up.SignUpScreen

sealed class RouteScreenAuth(val route: String) {
    object SignUp : RouteScreenAuth("signUp")
    object SignIn : RouteScreenAuth("signIn")
}

@Composable
internal fun AuthNavigation(
    modifier: Modifier = Modifier,
    authState: AuthState
) {
    NavHost(
        navController = authState.navController,
        startDestination = RouteScreenAuth.SignIn.route,
        modifier = modifier
    ) {
        addSignUp(authState)
        addSignIn(authState)
    }
}

private fun NavGraphBuilder.addSignUp(
    authState: AuthState
) {
    composable(route = RouteScreenAuth.SignUp.route) {
        SignUpScreen()
    }
}

private fun NavGraphBuilder.addSignIn(
    authState: AuthState
) {
    composable(route = RouteScreenAuth.SignIn.route) {
        SignInScreen()
    }
}