package com.example.mvi_shopping_cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_shopping_cart.data.repositoryimp.CartRepositoryImpl
import com.example.mvi_shopping_cart.domain.usecase.AddItemToCartUseCase
import com.example.mvi_shopping_cart.domain.usecase.GetCartItemsUseCase
import com.example.mvi_shopping_cart.domain.usecase.RemoveItemFromCartUseCase
import com.example.mvi_shopping_cart.domain.usecase.UpdateCartItemQuantityUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CartViewModel : ViewModel() {
    private val cartRepository = CartRepositoryImpl()
    private val getCartItemsUseCase = GetCartItemsUseCase(cartRepository)
    private val addItemToCartUseCase = AddItemToCartUseCase(cartRepository)
    private val removeItemFromCartUseCase = RemoveItemFromCartUseCase(cartRepository)
    private val updateCartItemQuantityUseCase = UpdateCartItemQuantityUseCase(cartRepository)

    private val _cartState = MutableStateFlow<CartState>(CartState.Loading)
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()
    init {
        observeCartItems()
    }

    private fun observeCartItems() {
        viewModelScope.launch {
            getCartItemsUseCase().collectLatest { items ->
                _cartState.value = CartState.Success(items)
            }
        }
    }

    fun handleIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.AddItem -> addItem(intent.item)
            is CartIntent.RemoveItem -> removeItem(intent.itemId)
            is CartIntent.UpdateQuantity -> updateQuantity(intent.itemId, intent.newQuantity)
        }
    }

    private fun addItem(cartItem: CartItem) {
        viewModelScope.launch {
            _cartState.value = CartState.Loading
            delay(500)
            addItemToCartUseCase(cartItem)
        }
    }

    private fun removeItem(itemId: String) {
        viewModelScope.launch {
            removeItemFromCartUseCase(itemId)
        }
    }

    private fun updateQuantity(itemId: String, newQuantity: Int) {
        viewModelScope.launch {
            updateCartItemQuantityUseCase(itemId, newQuantity)
        }
    }
}
