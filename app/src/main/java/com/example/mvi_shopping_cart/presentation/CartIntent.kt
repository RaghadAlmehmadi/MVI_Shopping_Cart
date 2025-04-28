package com.example.mvi_shopping_cart.presentation


sealed class CartIntent {
    data class AddItem(val item: CartItem) : CartIntent()
    data class RemoveItem(val itemId: String) : CartIntent()
    data class UpdateQuantity(val itemId: String, val newQuantity: Int) : CartIntent()
}
