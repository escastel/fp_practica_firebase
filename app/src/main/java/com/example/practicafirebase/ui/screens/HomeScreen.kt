package com.example.practicafirebase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicafirebase.ui.components.CustomButton
import com.example.practicafirebase.ui.theme.PracticaFirebaseTheme
import com.example.practicafirebase.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
//    viewModel: HomeViewModel = viewModel()
) {
    Scaffold() { paddingValues ->
//        val products by viewModel.products.collectAsState()
        val name by remember { mutableStateOf("") }
        val price by remember { mutableStateOf("") }
        val descripcion by remember { mutableStateOf("") }
        val imageUrl by remember { mutableStateOf("") }

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
                IconButton(onClick = { /*TODO*/ }){
                    Icon(
                        imageVector = Icons.Outlined.ExitToApp,
                        contentDescription = null
                    )
                }
            }
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Precio") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Descripcion") },
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "URL imagen") },
                modifier = Modifier.fillMaxWidth()
            )

            CustomButton(
                text = "Agregar Producto",
                onClick = {}
            )
//            LazyColumn(){
//                items(products) { product ->
//
//                }
//            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    PracticaFirebaseTheme {
        HomeScreen()
    }
}