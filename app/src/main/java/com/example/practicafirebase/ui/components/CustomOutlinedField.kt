package com.example.practicafirebase.ui.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomOutlinedField(
    label: String,
    icon: Boolean
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text(text = label) },
        trailingIcon = {
            if (icon){
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = ""
                )
            }
        },
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
            icon = true
        )
    }
}