package com.example.practicafirebase.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.practicafirebase.ui.screens.DetailsScreen
import com.example.practicafirebase.ui.screens.HomeScreen
import com.example.practicafirebase.ui.screens.LoginScreen
import com.example.practicafirebase.ui.screens.RegisterScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation(auth: FirebaseAuth) {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(Routes.Login)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Routes.Login -> NavEntry(key) {
                    LoginScreen(
                        auth = auth,
                        onEnterClick = { backStack.add(Routes.Home) },
                        onRegisterClick = { backStack.add(Routes.Register) }
                    )
                }
                is Routes.Register -> NavEntry(key) {
                    RegisterScreen(
                        auth = auth,
                        onCancelClick = { backStack.removeLastOrNull() },
                        onRegisterClick = { backStack.removeLastOrNull() }
                    )
                }
                is Routes.Home -> NavEntry(key) {
                    HomeScreen(
                        auth = auth,
                        onExitClick = { backStack.removeLastOrNull() },
                        onDetailsClick = { name, price, description, imageUrl ->
                            backStack.add(Routes.Details(
                                name = name,
                                price = price,
                                description = description,
                                imageUrl = imageUrl
                            ))
                        }
                    )
                }
                is Routes.Details -> NavEntry(key) {
                    DetailsScreen(
                        name = key.name,
                        price = key.price,
                        description = key.description,
                        imageUrl = key.imageUrl,
                        onBackClick = { backStack.removeLastOrNull() }
                    )
                }
                else -> NavEntry(key = Routes.Error){}
            }
        },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        }
    )
}