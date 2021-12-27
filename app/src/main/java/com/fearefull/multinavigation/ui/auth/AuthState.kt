package com.fearefull.multinavigation.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAuthState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    AuthState(navController)
}

class AuthState(
    val navController: NavHostController
) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateBack() = navController.popBackStack()
}