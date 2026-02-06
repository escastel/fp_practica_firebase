package com.example.practicafirebase.state

import androidx.annotation.StringRes

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val password2: String = "",
    val errorEmail: Boolean = false,
    val errorPass: Boolean = false,
    @StringRes val errorDialogMsg: Int = 0,
    val showErrorDialog: Boolean = false
)