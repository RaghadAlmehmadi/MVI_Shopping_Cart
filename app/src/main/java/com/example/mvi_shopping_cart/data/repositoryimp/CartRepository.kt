package com.example.mvi_shopping_cart.data.repositoryimp

import com.example.mvi_shopping_cart.presentation.CartItem
import com.example.mvi_shopping_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CartRepositoryImpl : CartRepository {

    private val cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartItems.asStateFlow()
    }

    override suspend fun addItem(cartItem: CartItem) {
        val currentList = cartItems.value.toMutableList()
        currentList.add(cartItem)
        cartItems.value = currentList
    }

    override suspend fun UpdateQuantity(cartItem: String, newQuantity: Int) {
       }


}
