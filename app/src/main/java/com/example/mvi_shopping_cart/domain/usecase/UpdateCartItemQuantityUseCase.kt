package com.example.mvi_shopping_cart.domain.usecase

import com.example.mvi_shopping_cart.domain.repository.CartRepository

class UpdateCartItemQuantityUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(itemId: String, newQuantity: Int) {
    }
}
