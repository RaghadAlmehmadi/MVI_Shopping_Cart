package com.example.mvi_shopping_cart.domain.usecase

import com.example.mvi_shopping_cart.presentation.CartItem
import com.example.mvi_shopping_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemsUseCase(private val repository: CartRepository) {
    operator fun invoke(): Flow<List<CartItem>> {
        return repository.getCartItems()
    }
}
