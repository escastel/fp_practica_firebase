package com.example.practicafirebase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomCard(
    name: String,
    price: String,
    onSearchClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card{
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = price)
            }

            Row {
                IconButton( onClick = onSearchClick ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
                IconButton( onClick = onEditClick ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = ""
                    )
                }
                IconButton( onClick = onDeleteClick ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomCardPreview(){
    PracticaFirebaseTheme {
        CustomCard(
            name = "Camiseta",
            price = "97.89",
            onSearchClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}

