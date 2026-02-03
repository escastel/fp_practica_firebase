package com.example.practicafirebase.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicafirebase.R
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
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
            icon = false
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomOutlinedField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.label_password),
            icon = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomOutlinedField(
            value = password2,
            onValueChange = { password2 = it },
            label = stringResource(R.string.label_repeat_password),
            icon = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            text = stringResource(R.string.btn_register),
            onClick = {
                if (password.equals(password2)){
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener { user ->
                            onRegisterClick()
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firebase", "Error en la creación de usuario ${e.message}")
                        }
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            text = stringResource(R.string.btn_cancel),
            onClick = onCancelClick
        )

    }
}