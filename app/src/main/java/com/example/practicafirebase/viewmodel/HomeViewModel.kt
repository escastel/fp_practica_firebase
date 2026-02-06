package com.example.practicafirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practicafirebase.model.Product
import com.example.practicafirebase.state.HomeUiState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val db = Firebase.firestore
    private val productsCollection = db.collection("products")

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState


    init {
        getProducts()
    }

    /* Métodos de estado */
    fun updateName(newName: String){
        _uiState.value = _uiState.value.copy(name = newName)
    }

    fun updatePrice(newPrice: String) {
        try {
            newPrice.toDouble()
            _uiState.value = _uiState.value.copy(price = newPrice)
            _uiState.value = _uiState.value.copy(priceError = false)
        } catch (
            e: NumberFormatException
        ) {
            _uiState.value = _uiState.value.copy(priceError = true)
            e.message
        }
    }

    fun updateDescription(newDescription: String) {
        _uiState.value = _uiState.value.copy(description = newDescription)
    }

    fun updateImageUrl(newImageUrl: String) {
        _uiState.value = _uiState.value.copy(imageUrl = newImageUrl)
    }

    fun cleanState(){
        _uiState.value = HomeUiState()
    }

    /* Métodos CRUD */
    private fun getProducts() {
        productsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val productsList = snapshot.documents.mapNotNull { doc ->
                    val product = doc.toObject(Product::class.java)
                    product?.id = doc.id
                    product
                }
                _products.value = productsList
            }
        }
    }

    fun addProduct() {
        val product = Product(
            name = _uiState.value.name,
            price = _uiState.value.price.toDouble(),
            description = _uiState.value.description,
            imageUrl = _uiState.value.imageUrl
        )

        productsCollection.add(product)
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al guardar: ${e.message}", e)
            }
            .addOnSuccessListener { }
            .addOnCompleteListener { }

        cleanState()
    }

    fun deleteProduct(id: String) {
        productsCollection.document(id)
            .delete()
            .addOnFailureListener {
                Log.i("Firebase", "Borrado correctamente")
            }
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al eliminar: ${e.message}", e)
            }
    }

    fun updateProduct(id: String) {
        val data = mutableMapOf<String, Any>()

        if (_uiState.value.name.isNotBlank()) {
            data["name"] = _uiState.value.name
        }
        if (_uiState.value.price.isNotBlank()) {
            data["price"] = _uiState.value.price.toDouble()
        }
        if (_uiState.value.description.isNotBlank()) {
            data["description"] = _uiState.value.description
        }
        if (_uiState.value.imageUrl.isNotBlank()) {
            data["imageUrl"] = _uiState.value.imageUrl
        }

        productsCollection.document(id)
            .update(data)
            .addOnSuccessListener {
                Log.i("Firebase", "Actualizado correctamente")
            }
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al actualizar: ${e.message}", e)
            }

        cleanState()
    }
}