package com.example.practicafirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practicafirebase.R
import com.example.practicafirebase.state.LoginUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState
    val emailPattern: Pattern = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
    )

    init {
        _uiState.value = LoginUiState()
    }

    fun updateEmail(newEmail: String){
        if (emailPattern.matcher(newEmail).matches()){
            _uiState.value = _uiState.value.copy(errorEmail = false)
        } else {
            _uiState.value = _uiState.value.copy(errorEmail = true)
        }
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String){
        if (newPassword.length >= 6){
            _uiState.value = _uiState.value.copy(errorPass = false)
        } else {
            _uiState.value = _uiState.value.copy(errorPass = true)
        }
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun updateShowDialog(newShowDialog: Boolean){
        _uiState.value = _uiState.value.copy(showErrorDialog = newShowDialog)
    }

    fun updateDialogMsg(newDialogMsg: Int){
        _uiState.value = _uiState.value.copy(errorDialogMsg = newDialogMsg)
    }

    fun singIn(auth: FirebaseAuth, onEnterClick: () -> Unit){
        if (_uiState.value.email.isBlank() || _uiState.value.password.isBlank()) {
            updateShowDialog(true)
            updateDialogMsg(R.string.error_empty_fields)
            return
        }

        auth.signInWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
            .addOnSuccessListener { user ->
                onEnterClick()
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error en login ${e.message}")
                updateShowDialog(true)
                updateDialogMsg(R.string.error_login_message)
            }
    }
}