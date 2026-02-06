package com.example.practicafirebase.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val msgError: String = "",
    val showErrorDialog: Boolean = false
)