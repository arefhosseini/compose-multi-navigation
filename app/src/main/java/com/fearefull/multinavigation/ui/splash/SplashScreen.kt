package com.fearefull.multinavigation.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fearefull.multinavigation.base.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    state: SplashNavigator.State,
    effectFlow: Flow<SplashNavigator.Effect>?,
    onEventSent: (event: SplashNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: SplashNavigator.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is SplashNavigator.Effect.Navigation.ToAuth ->
                    onNavigationSent(effect)
                is SplashNavigator.Effect.Navigation.ToMain ->
                    onNavigationSent(effect)
            }
        }?.collect()
    }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Splash")
    }
}