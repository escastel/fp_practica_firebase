package com.example.practicafirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practicafirebase.model.Product
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val db = Firebase.firestore
    private val productsCollection = db.collection("products")

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        getProducts()
    }

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

    fun addProduct(name: String, price: Double, description: String, imageUrl: String) {
        val product =
            Product(name = name, price = price, description = description, imageUrl = imageUrl)
        productsCollection.add(product)
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al guardar: ${e.message}", e)
            }
            .addOnSuccessListener { }
            .addOnCompleteListener { }
    }

    private fun deleteProduct(id: String) {
        productsCollection.document(id)
            .delete()
            .addOnFailureListener {
                Log.i("Firebase", "Borrado correctamente")
            }
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al eliminar: ${e.message}", e)
            }
    }

    private fun updateProduct(
        id: String,
        name: String,
        price: Double?,
        description: String,
        imageUrl: String
    ) {
        val data = mutableMapOf<String, Any>()

        if (name.isNotEmpty() && name.isNotBlank()) {
            data["name"] = name
        }
        if (price != null) {
            data["price"] = price
        }
        if (description.isNotEmpty() && description.isNotBlank()) {
            data["description"] = description
        }
        if (imageUrl.isNotEmpty() && imageUrl.isNotBlank()) {
            data["imageUrl"] = imageUrl
        }

        productsCollection.document(id)
            .update(data)
            .addOnSuccessListener {
                Log.i("Firebase", "Actualizado correctamente")
            }
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al actualizar: ${e.message}", e)
            }
        }
    }