package com.example.practicafirebase.state

data class HomeUiState(
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val priceError: Boolean = false
)