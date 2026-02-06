package com.example.practicafirebase.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorEmail: Boolean = false,
    val errorPass: Boolean = false,
    val showErrorDialog: Boolean = false
)