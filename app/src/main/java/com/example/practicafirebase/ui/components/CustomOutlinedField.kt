package com.example.practicafirebase.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

//TODO: Añadir que se vea la contraseña al clickear sobre el icono.
//TODO: Añadir el tipo de teclado.
@Composable
fun CustomOutlinedField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: Boolean
) {
    var seeText = if (icon) false else true
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        trailingIcon = {
            if (icon){
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "",
                    modifier = Modifier.clickable { seeText = !seeText }
                )
            }
        },
        visualTransformation = if (!seeText) PasswordVisualTransformation('*') else VisualTransformation.None,
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
            icon = true,
            value = "",
            onValueChange = {}
        )
    }
}