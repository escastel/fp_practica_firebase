package com.example.practicafirebase.ui.screens

import androidx.compose.animation.animateContentSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.components.CustomAlertDialog
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.components.CustomOutlinedField
import com.example.practicafirebase.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(
    auth: FirebaseAuth,
    onCancelClick: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cleanState()
    }

    Scaffold { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(paddingValues)
                .animateContentSize()
        ) {
            Text(
                text = stringResource(R.string.title_register),
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedField(
                value = uiState.email,
                onValueChange = { viewModel.updateEmail(it) },
                label = stringResource(R.string.label_email),
                passwd = false,
                error = uiState.errorEmail,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedField(
                value = uiState.password,
                onValueChange = { viewModel.updatePassword(it) },
                label = stringResource(R.string.label_password),
                passwd = true,
                error = uiState.errorPass,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedField(
                value = uiState.password2,
                onValueChange = { viewModel.updatePassword2(it) },
                label = stringResource(R.string.label_repeat_password),
                passwd = true,
                error = uiState.errorPass,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.btn_register),
                onClick = {
                    viewModel.createUser(auth, onRegisterClick)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.btn_cancel),
                onClick = onCancelClick
            )

        }
    }

    if (uiState.showErrorDialog){
        CustomAlertDialog(
            btnText = stringResource(R.string.btn_exit),
            title = stringResource(R.string.error_register),
            message = stringResource(uiState.errorDialogMsg),
            onDismissDialog = { viewModel.updateShowDialog(false) }
        )
    }
}