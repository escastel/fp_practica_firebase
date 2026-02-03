package com.example.practicafirebase.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomAlertDialog(
    btnText: String,
    title: String,
    message: String,
    onDismissDialog: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissDialog,
        confirmButton = {
            Button(onClick = onDismissDialog){
                Text(text = btnText)
            }
        },
        title = { Text(text = title) },
        text = { Text(text = message) },
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

@Preview
@Composable
fun CustomAlertDialogPreview(){
    PracticaFirebaseTheme {
        CustomAlertDialog(
            btnText = stringResource(R.string.btn_exit),
            title = stringResource(R.string.error_login),
            message = stringResource(R.string.error_login_message),
            onDismissDialog = { }
        )
    }
}