package com.example.mvi_shopping_cart.domain.usecase

import com.example.mvi_shopping_cart.presentation.CartItem
import com.example.mvi_shopping_cart.domain.repository.CartRepository


class AddItemToCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(cartItem: CartItem) {
        repository.addItem(cartItem)
    }
}
