package com.fearefull.multinavigation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberMainState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    MainState(navController)
}

class MainState(
    val navController: NavHostController
) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateBack() = navController.popBackStack()

    fun onMainBottomNavigationSelected(selected: RouteScreenMain) {
        if (selected.route != currentRoute) {
            navController.navigate(selected.route) {
                launchSingleTop = true
                restoreState = true

                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }
}