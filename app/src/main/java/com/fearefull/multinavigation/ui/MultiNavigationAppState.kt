package com.fearefull.multinavigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberMultiNavigationAppState(
    finishActivity: () -> Unit,
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    MultiNavigationAppState(navController, finishActivity)
}

class MultiNavigationAppState(
    val navController: NavHostController,
    finishActivity: () -> Unit
) {

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    private fun navigateBack() = navController.popBackStack()

    fun navigateToMain(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigateAndReplaceStartRoute(RouteScreen.Main.route)
        }
    }

    fun navigateToAuth(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigateAndReplaceStartRoute(RouteScreen.Auth.route)
        }
    }

    fun navigateToSignUp(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(RouteScreenAuth.SignUp.route)
        }
    }

    fun navigateToSignIn(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navigateBack() // pop sign up
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

fun NavHostController.navigateAndReplaceStartRoute(newHomeRoute: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute)
}