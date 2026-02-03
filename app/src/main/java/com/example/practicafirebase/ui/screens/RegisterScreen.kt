package com.example.practicafirebase.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.components.CustomOutlinedField
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun RegisterScreen() {
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
            onClick = {} //TODO: Registro
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            text = stringResource(R.string.btn_cancel),
            onClick = {} // TODO: Navegar al login
        )

    }
}

@Preview (showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PracticaFirebaseTheme {
        RegisterScreen()
    }
}