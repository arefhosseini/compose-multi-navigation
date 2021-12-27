package com.fearefull.multinavigation.ui.auth.sign_in

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
fun SignInScreen(
    state: SignInNavigator.State,
    effectFlow: Flow<SignInNavigator.Effect>?,
    onEventSent: (event: SignInNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: SignInNavigator.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is SignInNavigator.Effect.Navigation.ToSignUp ->
                    onNavigationSent(effect)
                is SignInNavigator.Effect.Navigation.ToMain ->
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
                onEventSent(SignInNavigator.Event.ToSignUpRequested)
            },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("Navigate to Sign up")
        }
        Button(
            onClick = {
                onEventSent(SignInNavigator.Event.SignInRequested(User("email")))
            },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("Sign in")
        }
    }
}