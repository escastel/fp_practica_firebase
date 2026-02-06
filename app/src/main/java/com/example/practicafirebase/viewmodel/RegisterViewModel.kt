package com.example.practicafirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practicafirebase.R
import com.example.practicafirebase.state.RegisterUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

class RegisterViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState
    val emailPattern: Pattern = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
    )

    init {
        _uiState.value = RegisterUiState()
    }

    fun updateEmail(newEmail: String){
        if (newEmail.isNotBlank() && emailPattern.matcher(newEmail).matches()){
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

    fun updatePassword2(newPassword2: String){
        if (newPassword2.length >= 6){
            _uiState.value = _uiState.value.copy(errorPass = false)
        } else {
            _uiState.value = _uiState.value.copy(errorPass = true)
        }
        _uiState.value = _uiState.value.copy(password2 = newPassword2)
    }

    fun updateShowDialog(newShowDialog: Boolean){
        _uiState.value = _uiState.value.copy(showErrorDialog = newShowDialog)
    }

    fun updateDialogMsg(newDialogMsg: Int){
        _uiState.value = _uiState.value.copy(errorDialogMsg = newDialogMsg)
    }


    fun createUser(auth: FirebaseAuth, onRegisterClick: () -> Unit) {
        if (_uiState.value.email.isBlank() || _uiState.value.password.isBlank() || _uiState.value.password2.isBlank()) {
            updateShowDialog(true)
            updateDialogMsg(R.string.error_empty_fields)
            return
        }
        if (_uiState.value.password != _uiState.value.password2) {
            updateShowDialog(true)
            updateDialogMsg(R.string.error_identical_passwd_message)
            return
        }

        auth.createUserWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
            .addOnSuccessListener { user ->
                onRegisterClick()
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error en la creación de usuario ${e.message}")
                updateShowDialog(true)
                updateDialogMsg(R.string.error_invalid_fields)
            }
    }

    fun cleanState(){
        _uiState.value = RegisterUiState()
    }
}