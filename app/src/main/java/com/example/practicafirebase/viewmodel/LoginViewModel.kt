package com.example.practicafirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practicafirebase.state.LoginUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun updateEmail(newEmail: String){
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String){
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun updateShowDialog(newShowDialog: Boolean){
        _uiState.value = _uiState.value.copy(showErrorDialog = newShowDialog)
    }

    /* La aplicacion cierra cuando le pasas a firebase una cadena vacia*/
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