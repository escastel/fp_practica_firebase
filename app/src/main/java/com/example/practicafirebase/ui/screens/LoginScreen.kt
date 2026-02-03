package com.example.practicafirebase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun LoginScreen() {
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
            label = stringResource(R.string.label_email),
            icon = false
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomOutlinedField(
            label = stringResource(R.string.label_password),
            icon = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            text = stringResource(R.string.btn_login),
            onClick = {} //TODO: Autentificacion
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.no_account))
            Text(text = stringResource(R.string.btn_register)) //TODO: Ir a la pantalla de registro
        }
    }
}

@Preview (showBackground = true)
@Composable
fun LoginScreenPreview() {
    PracticaFirebaseTheme {
        LoginScreen()
    }
}