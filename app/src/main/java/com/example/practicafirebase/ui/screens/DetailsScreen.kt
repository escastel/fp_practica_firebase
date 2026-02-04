package com.example.practicafirebase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.components.ProductDetails
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme

@Composable
fun DetailsScreen(
    name: String,
    price: String,
    description: String,
    imageUrl: String,
    onBackClick: () -> Unit,
) {
    Scaffold { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .padding(paddingValues)
        ) {
            if (imageUrl.isNotEmpty() && imageUrl.isNotBlank()){
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            } else {
                AsyncImage(
                    model = "https://xumuxua.com/wp-content/uploads/2021/07/SINFOTO.jpg",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }

            ProductDetails(
                modifier = Modifier.weight(1f),
                name = name,
                price = price,
                description = description
            )

            CustomButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.btn_back),
                onClick = onBackClick,
            )
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview(){
    PracticaFirebaseTheme {
        DetailsScreen(
            name = "Camiseta Málaga CF",
            price = "69.95",
            description = "Camiseta oficial del Málaga CF",
            imageUrl = "",
            onBackClick = {}
        )
    }
}