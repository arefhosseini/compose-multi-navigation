package com.fearefull.multinavigation.ui.auth.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fearefull.multinavigation.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.fearefull.multinavigation.data.model.local.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SignUpScreen(
    state: SignUpNavigator.State,
    effectFlow: Flow<SignUpNavigator.Effect>?,
    onEventSent: (event: SignUpNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: SignUpNavigator.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is SignUpNavigator.Effect.Navigation.ToSignIn ->
                    onNavigationSent(effect)
                is SignUpNavigator.Effect.Navigation.ToMain ->
                    onNavigationSent(effect)
            }
        }?.collect()
    }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                onEventSent(SignUpNavigator.Event.ToSignInRequested)
            },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("Navigate to Sign in")
        }
        Button(
            onClick = {
                onEventSent(SignUpNavigator.Event.SignUpRequested(User("email")))
            },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("Sign up")
        }
    }
}