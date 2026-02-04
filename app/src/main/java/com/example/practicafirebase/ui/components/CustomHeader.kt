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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomHeader(
    email: String? = "",
    onExitClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Bienvenido/a $email",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onExitClick,
            modifier = Modifier.weight(0.2f)
        ){
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
            email = "ejempafdfdafffggsfgdsgdfglo@gmail.com",
            onExitClick = {}
        )
    }
}