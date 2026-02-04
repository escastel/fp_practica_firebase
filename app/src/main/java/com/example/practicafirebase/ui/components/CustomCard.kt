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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun CustomCard(
    name: String,
    price: String,
) {
    Card{
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text(name)
                Text(price)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = null)
                Icon(Icons.Default.Create, contentDescription = "")
                Icon(Icons.Default.Delete, contentDescription = null)
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
            price = "97.89"
        )
    }
}

