package com.example.practicafirebase.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomOutlinedField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    passwd: Boolean,
    error: Boolean,
    keyboardOptions: KeyboardOptions
) {
    var visibleText by remember { mutableStateOf(false) }
    if (!passwd) visibleText = true

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        trailingIcon = {
            if (passwd){
                IconButton( onClick = { visibleText = !visibleText } ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = ""
                    )
                }
            }
        },
        supportingText = {
            if (error && !passwd) {
                Text(text = stringResource(R.string.error_email))
            } else if (error) {
                Text(text = stringResource(R.string.error_password))
            }
        },
        isError = error,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (!visibleText) PasswordVisualTransformation('*') else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
    )
}

@Preview (showBackground = true)
@Composable
fun CustomOutlineFieldPreview(){
    PracticaFirebaseTheme {
        CustomOutlinedField(
            label = stringResource(R.string.label_password),
            passwd = true,
            value = "",
            error = false,
            onValueChange = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}