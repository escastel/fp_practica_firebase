package com.example.practicafirebase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.components.CustomCard
import com.example.practicafirebase.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onExitClick: () -> Unit
) {
    Scaffold { paddingValues ->
        val products by viewModel.products.collectAsState()

        var name by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var imageUrl by remember { mutableStateOf("") }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Bienvenido ester@gmail.com")
                IconButton(onClick = onExitClick){
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                        contentDescription = stringResource(R.string.btn_exit)
                    )
                }
            }
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(R.string.label_name)) },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = price,
                onValueChange = { price = it },
                label = { Text(text = stringResource(R.string.label_price)) },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = stringResource(R.string.label_description)) },
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )
            TextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text(text = stringResource(R.string.label_image_url)) },
                modifier = Modifier.fillMaxWidth()
            )

            CustomButton(
                text = stringResource(R.string.btn_create_product),
                onClick = { viewModel.addProduct(name, price.toDouble(), description, imageUrl) }
            )

            LazyColumn{
                items(products) { product ->
                    CustomCard(product.name, product.price.toString())
                }
            }
        }
    }
}