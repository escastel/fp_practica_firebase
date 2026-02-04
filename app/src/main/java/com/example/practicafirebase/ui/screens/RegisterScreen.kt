package com.example.practicafirebase.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.components.CustomAlertDialog
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.components.CustomOutlinedField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(
    auth: FirebaseAuth,
    onCancelClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var showErrorIdenticalPasswd by remember { mutableStateOf(false) }
    var showErrorInvalidPasswd by remember { mutableStateOf(false) }


    Scaffold { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(R.string.title_register),
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.label_email),
                passwd = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.label_password),
                passwd = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedField(
                value = password2,
                onValueChange = { password2 = it },
                label = stringResource(R.string.label_repeat_password),
                passwd = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.btn_register),
                onClick = {
                    if (password == password2){
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener { user ->
                                onRegisterClick()
                            }
                            .addOnFailureListener { e ->
                                Log.e("Firebase", "Error en la creación de usuario ${e.message}")
                                showErrorInvalidPasswd = true
                            }
                    } else {
                        showErrorIdenticalPasswd = true
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.btn_cancel),
                onClick = onCancelClick
            )

        }

        if (showErrorIdenticalPasswd){
            CustomAlertDialog(
                btnText = stringResource(R.string.btn_exit),
                title = stringResource(R.string.error_register),
                message = stringResource(R.string.error_identical_passwd_message),
                onDismissDialog = { showErrorIdenticalPasswd = false }
            )
        }

        if (showErrorInvalidPasswd){
            CustomAlertDialog(
                btnText = stringResource(R.string.btn_exit),
                title = stringResource(R.string.error_register),
                message = stringResource(R.string.error_invalid_passwd_message),
                onDismissDialog = { showErrorInvalidPasswd = false }
            )
        }
    }
}