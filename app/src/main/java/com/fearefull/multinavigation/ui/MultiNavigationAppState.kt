package com.fearefull.multinavigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
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

    fun navigateBack() = navController.popBackStack()
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED