package com.example.mvi_shopping_cart.presentation


sealed class CartState {
    object Loading : CartState()
    data class Success(val items: List<CartItem>) : CartState()
    data class Error(val message: String) : CartState()
}

data class CartItem(
    val id: String,
    val name: String,
    val quantity: Int
)
