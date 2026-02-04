package com.example.practicafirebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
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
                        onExitClick = { backStack.removeLastOrNull() }
                    )
                }
                else -> NavEntry(key = Routes.Error){}
            }
        }
    )
}