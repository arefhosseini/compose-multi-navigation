package com.fearefull.multinavigation.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fearefull.multinavigation.R

@Composable
internal fun MainBottomNavigation(
    selectedNavigation: RouteScreenMain,
    onNavigationSelected: (RouteScreenMain) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = contentColorFor(MaterialTheme.colors.surface),
        modifier = modifier
    ) {
        MainNavigationItems.forEach { item ->
            BottomNavigationItem(
                selected = selectedNavigation == item.routeScreenMain,
                onClick = { onNavigationSelected(item.routeScreenMain) },
                label = { Text(stringResource(item.labelResId)) },
                icon = {
                    MainNavigationItemIcon(
                        item = item,
                        selected = selectedNavigation == item.routeScreenMain
                    )
                }
            )
        }
    }
}

@Composable
private fun MainNavigationItemIcon(item: MainNavigationItem, selected: Boolean) {
    val painter = when (item) {
        is MainNavigationItem.ResourceIcon -> painterResource(item.iconResId)
        is MainNavigationItem.ImageVectorIcon -> rememberVectorPainter(item.iconImageVector)
    }
    val selectedPainter = when (item) {
        is MainNavigationItem.ResourceIcon -> item.selectedIconResId?.let { painterResource(it) }
        is MainNavigationItem.ImageVectorIcon -> item.selectedImageVector?.let { rememberVectorPainter(it) }
    }

    if (selectedPainter != null) {
        Crossfade(targetState = selected) {
            Icon(
                painter = if (it) selectedPainter else painter,
                contentDescription = stringResource(item.contentDescriptionResId),
            )
        }
    } else {
        Icon(
            painter = painter,
            contentDescription = stringResource(item.contentDescriptionResId),
        )
    }
}

private sealed class MainNavigationItem(
    val routeScreenMain: RouteScreenMain,
    @StringRes val labelResId: Int,
    @StringRes val contentDescriptionResId: Int,
) {
    class ResourceIcon(
        routeScreen: RouteScreenMain,
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        @DrawableRes val iconResId: Int,
        @DrawableRes val selectedIconResId: Int? = null,
    ) : MainNavigationItem(routeScreen, labelResId, contentDescriptionResId)

    class ImageVectorIcon(
        routeScreenMain: RouteScreenMain,
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        val iconImageVector: ImageVector,
        val selectedImageVector: ImageVector? = null,
    ) : MainNavigationItem(routeScreenMain, labelResId, contentDescriptionResId)
}

private val MainNavigationItems = listOf(
    MainNavigationItem.ResourceIcon(
        routeScreen = RouteScreenMain.Home,
        labelResId = R.string.home,
        contentDescriptionResId = R.string.home,
        iconResId = R.drawable.ic_outline_home_24,
        selectedIconResId = R.drawable.ic_round_home_24,
    ),
    MainNavigationItem.ResourceIcon(
        routeScreen = RouteScreenMain.Other,
        labelResId = R.string.other,
        contentDescriptionResId = R.string.other,
        iconResId = R.drawable.ic_outline_local_offer_24,
        selectedIconResId = R.drawable.ic_round_local_offer_24,
    ),
    MainNavigationItem.ResourceIcon(
        routeScreen = RouteScreenMain.Profile,
        labelResId = R.string.profile,
        contentDescriptionResId = R.string.profile,
        iconResId = R.drawable.ic_round_person_outline_24,
        selectedIconResId = R.drawable.ic_round_person_24,
    ),
)
