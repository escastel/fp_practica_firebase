package com.example.practicafirebase.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.components.CustomOutlinedField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    auth: FirebaseAuth,
    onEnterClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.title_login),
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

        CustomButton(
            text = stringResource(R.string.btn_login),
            onClick = {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { user ->
                        onEnterClick()
                    }
                    .addOnFailureListener { e ->
                        Log.e("Firebase", "Error en login ${e.message}")
                        showErrorDialog = true
                    }

            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.no_account))
            TextButton(
                onClick = onRegisterClick
            ) {
                Text(text = stringResource(R.string.btn_register))
            }
        }
    }

    if (showErrorDialog){
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            confirmButton = {
                Button(onClick = { showErrorDialog = false }){
                    Text(text = "Salir")
                }
            },
            title = { Text(text = "Error de autentificación") },
            text = { Text(text = "El email y/o la contraseña son incorrectas") },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.Red
                )
            }
        )
    }
}