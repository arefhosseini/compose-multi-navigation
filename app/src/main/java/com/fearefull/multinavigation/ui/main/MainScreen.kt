package com.fearefull.multinavigation.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import com.fearefull.multinavigation.ui.MultiNavigationAppState

@Composable
fun MainScreen(
    appState: MultiNavigationAppState
) {
    val mainState = rememberMainState()
    Scaffold(
        bottomBar = {
            val currentSelectedItem by mainState.navController.currentScreenAsState()
            MainBottomNavigation(
                selectedNavigation = currentSelectedItem,
                onNavigationSelected = mainState::onMainBottomNavigationSelected,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        MainNavigation(mainState = mainState, appState = appState)
    }
}

/**
 * Adds an [NavController.OnDestinationChangedListener] to this [NavController] and updates the
 * returned [State] which is updated as the destination changes.
 */
@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RouteScreenMain> {
    val selectedItem = remember { mutableStateOf<RouteScreenMain>(RouteScreenMain.DEFAULT_TAB) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == RouteScreenMain.Home.route } -> {
                    selectedItem.value = RouteScreenMain.Home
                }
                destination.hierarchy.any { it.route == RouteScreenMain.Other.route } -> {
                    selectedItem.value = RouteScreenMain.Other
                }
                destination.hierarchy.any { it.route == RouteScreenMain.Profile.route } -> {
                    selectedItem.value = RouteScreenMain.Profile
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}