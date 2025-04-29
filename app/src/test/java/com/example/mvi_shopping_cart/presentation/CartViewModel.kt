package com.example.mvi_shopping_cart.presentation

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {

    private lateinit var viewModel: CartViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CartViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial cartState should eventually be Success with empty list`() = runTest {
        advanceUntilIdle()
        val state = viewModel.cartState.value
        assertTrue(state is CartState.Success)//verify that a condition is true
        assertTrue((state as CartState.Success).items.isEmpty())
    }

    @Test
    fun `handleIntent AddItem should update cartState with new item`() = runTest {
        val item = CartItem(id = "1", name = "Water", quantity = 1)

        viewModel.handleIntent(CartIntent.AddItem(item))
        advanceTimeBy(600)
        advanceUntilIdle()

        val state = viewModel.cartState.value
        assertTrue(state is CartState.Success)
        assertEquals(
            1,
            (state as CartState.Success).items.size
        )//checks whether two values are equal
        assertEquals("Water", state.items.first().name)
    }

}
