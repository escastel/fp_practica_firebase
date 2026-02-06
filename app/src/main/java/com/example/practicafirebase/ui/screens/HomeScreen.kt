package com.example.practicafirebase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicafirebase.R
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.components.CustomCard
import com.example.practicafirebase.ui.components.CustomForm
import com.example.practicafirebase.ui.components.CustomHeader
import com.example.practicafirebase.viewmodel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    auth: FirebaseAuth,
    onExitClick: () -> Unit,
    onDetailsClick: (String, String, String, String) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    Scaffold { paddingValues ->
        val products by viewModel.products.collectAsState()
        val uiState by viewModel.uiState.collectAsState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            CustomHeader(
                email = auth.currentUser?.email,
                onExitClick = onExitClick
            )

            CustomForm(
                name = uiState.name,
                onNameChange = { viewModel.updateName(it) },
                price = uiState.price,
                onPriceChange = {viewModel.updatePrice(it) },
                errorPrice = uiState.priceError,
                description = uiState.description,
                onDescriptionChange = { viewModel.updateDescription(it) },
                imageUrl = uiState.imageUrl,
                onImageUrlChange = { viewModel.updateImageUrl(it) }
            )

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.btn_create_product),
                onClick = { viewModel.addProduct() }
            )

            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(products) { product ->
                    CustomCard(
                        name = product.name,
                        price = String.format("%.2f", product.price),
                        onSearchClick = {
                            onDetailsClick(
                                product.name,
                                String.format("%.2f", product.price),
                                product.description,
                                product.imageUrl
                            )
                        },
                        onEditClick = { viewModel.updateProduct(id = product.id) },
                        onDeleteClick = { viewModel.deleteProduct(id = product.id) }
                    )
                }
            }
        }
    }
}