package com.example.mvi_shopping_cart.domain.repository

import com.example.mvi_shopping_cart.presentation.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addItem(cartItem: CartItem)
    suspend fun UpdateQuantity(cartItem: String, newQuantity: Int)

}