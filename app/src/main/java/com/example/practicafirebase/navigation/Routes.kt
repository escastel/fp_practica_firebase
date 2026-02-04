package com.example.practicafirebase.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data object Login: Routes()

    @Serializable
    data object Register: Routes()

    @Serializable
    data object Home: Routes()

    @Serializable
    data class Details(
        val name: String,
        val price: String,
        val description: String,
        val imageUrl: String,
    ): Routes()

    @Serializable
    data object Error: Routes()
}