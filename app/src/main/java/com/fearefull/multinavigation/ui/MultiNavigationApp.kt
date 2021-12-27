package com.fearefull.multinavigation.ui

import androidx.compose.runtime.Composable
import com.fearefull.multinavigation.ui.theme.MultiNavigationTheme

@Composable
fun MultiNavigationApp(finishActivity: () -> Unit) {
    val appState = rememberMultiNavigationAppState(finishActivity)
    MultiNavigationTheme {
        AppNavigation(appState = appState)
    }
}