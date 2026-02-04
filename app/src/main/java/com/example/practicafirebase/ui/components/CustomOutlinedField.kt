package com.example.practicafirebase.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

//TODO: Añadir el tipo de teclado.
@Composable
fun CustomOutlinedField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    passwd: Boolean
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
        visualTransformation = if (!visibleText) PasswordVisualTransformation('*') else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
}

@Preview (showBackground = true)
@Composable
fun CustomOutlineField(){
    PracticaFirebaseTheme {
        CustomOutlinedField(
            label = stringResource(R.string.label_password),
            passwd = true,
            value = "",
            onValueChange = {}
        )
    }
}