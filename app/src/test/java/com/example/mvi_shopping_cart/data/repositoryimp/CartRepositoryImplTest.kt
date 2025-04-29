package com.example.mvi_shopping_cart.data.repositoryimp

import com.example.mvi_shopping_cart.presentation.CartItem
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CartRepositoryImplTest {

    private lateinit var repository: CartRepositoryImpl

    @Before
    fun setUp() {
        repository = CartRepositoryImpl()
    }


    @Test
    fun `getCartItems should initially be empty`() = runTest {
        val items = repository.getCartItems().first()
        assertTrue(items.isEmpty())
    }

    @Test
    fun `addItem should emit updated list with one item`() = runTest {
        val item = CartItem(id = "1", name = "Water", quantity = 1)
        repository.addItem(item)

        val result = repository.getCartItems().first()
        assertEquals(1, result.size)
        assertEquals("Water", result.first().name)
    }

    @Test
    fun `addItem should accumulate items`() = runTest {
        val item1 = CartItem(id = "1", name = "Water", quantity = 1)
        val item2 = CartItem(id = "2", name = "Chips", quantity = 3)

        repository.addItem(item1)
        repository.addItem(item2)

        val result = repository.getCartItems().first()
        assertEquals(2, result.size)
        assertTrue(result.any { it.name == "Water" })
        assertTrue(result.any { it.name == "Chips" })
    }
}
