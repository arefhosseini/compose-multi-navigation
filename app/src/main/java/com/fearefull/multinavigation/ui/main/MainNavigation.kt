package com.fearefull.multinavigation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fearefull.multinavigation.ui.RouteScreen
import com.fearefull.multinavigation.ui.main.home.HomeScreen
import com.fearefull.multinavigation.ui.main.other.OtherScreen
import com.fearefull.multinavigation.ui.main.profile.ProfileScreen

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
    mainState: MainState
) {
    NavHost(
        navController = mainState.navController,
        startDestination = RouteScreenMain.Home.route,
        modifier = modifier
    ) {
        addHomeTopLevel(mainState)
        addOtherTopLevel(mainState)
        addProfileTopLevel(mainState)
    }
}

private fun NavGraphBuilder.addHomeTopLevel(
    mainState: MainState
) {
    navigation(
        route = RouteScreenMain.Home.route,
        startDestination = RouteScreenMainLeaf.Home.createRoute(RouteScreenMain.Home)
    ) {
        addHome(mainState, RouteScreenMain.Home)
        addOther(mainState, RouteScreenMain.Home)
        addProfile(mainState, RouteScreenMain.Home)
    }
}

private fun NavGraphBuilder.addOtherTopLevel(
    mainState: MainState
) {
    navigation(
        route = RouteScreenMain.Other.route,
        startDestination = RouteScreenMainLeaf.Other.createRoute(RouteScreenMain.Other)
    ) {
        addHome(mainState, RouteScreenMain.Other)
        addOther(mainState, RouteScreenMain.Other)
        addProfile(mainState, RouteScreenMain.Other)
    }
}

private fun NavGraphBuilder.addProfileTopLevel(
    mainState: MainState
) {
    navigation(
        route = RouteScreenMain.Profile.route,
        startDestination = RouteScreenMainLeaf.Profile.createRoute(RouteScreenMain.Profile)
    ) {
        addHome(mainState, RouteScreenMain.Profile)
        addOther(mainState, RouteScreenMain.Profile)
        addProfile(mainState, RouteScreenMain.Profile)
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
    root: RouteScreenMain
) {
    composable(RouteScreenMainLeaf.Profile.createRoute(root)) {
        ProfileScreen()
    }
}