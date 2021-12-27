package com.fearefull.multinavigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fearefull.multinavigation.ui.auth.AuthScreen
import com.fearefull.multinavigation.ui.main.MainScreen
import com.fearefull.multinavigation.ui.splash.SplashScreen

sealed class RouteScreen(val route: String) {
    object Splash : RouteScreen("splash")
    object Auth : RouteScreen("auth")
    object Main : RouteScreen("main")
}

@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    appState: MultiNavigationAppState
) {
    NavHost(
        navController = appState.navController,
        startDestination = RouteScreen.Splash.route,
        modifier = modifier
    ) {
        addSplash(appState)
        addAuth(appState)
        addMain(appState)
    }
}

private fun NavGraphBuilder.addSplash(
    appState: MultiNavigationAppState
) {
    composable(route = RouteScreen.Splash.route) {
        SplashScreen()
    }
}

private fun NavGraphBuilder.addAuth(
    appState: MultiNavigationAppState
) {
    composable(route = RouteScreen.Splash.route) {
        AuthScreen()
    }
}

private fun NavGraphBuilder.addMain(
    appState: MultiNavigationAppState
) {
    composable(route = RouteScreen.Main.route) {
        MainScreen()
    }
}