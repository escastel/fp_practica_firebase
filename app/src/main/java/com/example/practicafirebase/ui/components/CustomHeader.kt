package com.example.practicafirebase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomHeader(
    email: String? = null,
    onExitClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Bienvenido/a $email")
        IconButton(onClick = onExitClick){
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                contentDescription = stringResource(R.string.btn_exit)
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun CustomHeaderPreview(){
    PracticaFirebaseTheme {
        CustomHeader(
            email = "ejemplo@gmail.com",
            onExitClick = {}
        )
    }
}