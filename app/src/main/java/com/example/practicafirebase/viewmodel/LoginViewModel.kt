package com.example.practicafirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practicafirebase.state.LoginUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

val EMAIL_PATTERN: Pattern = Pattern.compile(
    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
)

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun updateEmail(newEmail: String){
        if (newEmail.isNotBlank() && EMAIL_PATTERN.matcher(newEmail).matches()){
            _uiState.value = _uiState.value.copy(errorEmail = false)
            _uiState.value = _uiState.value.copy(email = newEmail)
        } else {
            _uiState.value = _uiState.value.copy(errorEmail = true)
        }
    }

    fun updatePassword(newPassword: String){
        if (newPassword.length >= 6){
            _uiState.value = _uiState.value.copy(errorPass = false)
            _uiState.value = _uiState.value.copy(password = newPassword)
        } else {
            _uiState.value = _uiState.value.copy(errorPass = true)
        }
    }

    fun updateShowDialog(newShowDialog: Boolean){
        _uiState.value = _uiState.value.copy(showErrorDialog = newShowDialog)
    }

    fun singIn(auth: FirebaseAuth, onEnterClick: () -> Unit){
        if (_uiState.value.email.isBlank() || _uiState.value.password.isBlank()) {
            updateShowDialog(true)
            return
        }

        auth.signInWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
            .addOnSuccessListener { user ->
                onEnterClick()
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error en login ${e.message}")
                updateShowDialog(true)
            }
    }
}