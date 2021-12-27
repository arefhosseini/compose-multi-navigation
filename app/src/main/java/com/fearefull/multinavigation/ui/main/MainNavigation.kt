package com.fearefull.multinavigation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fearefull.multinavigation.ui.MultiNavigationAppState
import com.fearefull.multinavigation.ui.main.home.HomeScreen
import com.fearefull.multinavigation.ui.main.other.OtherScreen
import com.fearefull.multinavigation.ui.main.profile.ProfileNavigator
import com.fearefull.multinavigation.ui.main.profile.ProfileScreen
import com.fearefull.multinavigation.ui.main.profile.ProfileViewModel

sealed class RouteScreenMain(val route: String) {
    object Home : RouteScreenMain("home")
    object Other : RouteScreenMain("other")
    object Profile : RouteScreenMain("profile")

    companion object {
        val DEFAULT_TAB = Home
    }
}

sealed class RouteScreenMainLeaf(val route: String) {
    fun createRoute(root: RouteScreenMain) = "${root.route}/$route"

    object Home : RouteScreenMainLeaf("home")
    object Other : RouteScreenMainLeaf("other")
    object Profile : RouteScreenMainLeaf("profile")
}

@Composable
internal fun MainNavigation(
    modifier: Modifier = Modifier,
    mainState: MainState,
    appState: MultiNavigationAppState
) {
    NavHost(
        navController = mainState.navController,
        startDestination = RouteScreenMain.Home.route,
        modifier = modifier
    ) {
        addHomeTopLevel(mainState, appState)
        addOtherTopLevel(mainState, appState)
        addProfileTopLevel(mainState, appState)
    }
}

private fun NavGraphBuilder.addHomeTopLevel(
    mainState: MainState,
    appState: MultiNavigationAppState
) {
    navigation(
        route = RouteScreenMain.Home.route,
        startDestination = RouteScreenMainLeaf.Home.createRoute(RouteScreenMain.Home)
    ) {
        addHome(mainState, RouteScreenMain.Home)
        addOther(mainState, RouteScreenMain.Home)
        addProfile(mainState, appState, RouteScreenMain.Home)
    }
}

private fun NavGraphBuilder.addOtherTopLevel(
    mainState: MainState,
    appState: MultiNavigationAppState
) {
    navigation(
        route = RouteScreenMain.Other.route,
        startDestination = RouteScreenMainLeaf.Other.createRoute(RouteScreenMain.Other)
    ) {
        addHome(mainState, RouteScreenMain.Other)
        addOther(mainState, RouteScreenMain.Other)
        addProfile(mainState, appState, RouteScreenMain.Other)
    }
}

private fun NavGraphBuilder.addProfileTopLevel(
    mainState: MainState,
    appState: MultiNavigationAppState
) {
    navigation(
        route = RouteScreenMain.Profile.route,
        startDestination = RouteScreenMainLeaf.Profile.createRoute(RouteScreenMain.Profile)
    ) {
        addHome(mainState, RouteScreenMain.Profile)
        addOther(mainState, RouteScreenMain.Profile)
        addProfile(mainState, appState, RouteScreenMain.Profile)
    }
}

private fun NavGraphBuilder.addHome(
    mainState: MainState,
    root: RouteScreenMain
) {
    composable(RouteScreenMainLeaf.Home.createRoute(root)) {
        HomeScreen()
    }
}

private fun NavGraphBuilder.addOther(
    mainState: MainState,
    root: RouteScreenMain
) {
    composable(RouteScreenMainLeaf.Other.createRoute(root)) {
        OtherScreen()
    }
}

private fun NavGraphBuilder.addProfile(
    mainState: MainState,
    appState: MultiNavigationAppState,
    root: RouteScreenMain
) {
    composable(RouteScreenMainLeaf.Profile.createRoute(root)) {
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is ProfileNavigator.Effect.Navigation.ToAuth -> {
                        appState.navigateToAuth(it)
                    }
                }
            }
        )
    }
}