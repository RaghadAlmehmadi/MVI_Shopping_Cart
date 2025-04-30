# MVI Shopping Cart

## Project Overview

The MVI Shopping Cart is an Android app that implements the **Model-View-Intent (MVI)** pattern. The goal is to create a clean and efficient shopping cart application using MVI, where each component has a clear responsibility, making it easy to manage UI states and handle user actions.

### Key Concepts of MVI
1. **Model**: Represents the data (`CartItem`), business logic, and state of the app.
2. **View**: Displays the UI based on the state provided by the ViewModel. It emits user Intents to the ViewModel.
3. **Intent**: User actions or events that the ViewModel processes (e.g., add item, remove item).
4. **State**: Represents the UI state (loading, success, error).

## Implemented Use Cases
Currently, only two use cases are implemented in the domain layer:

1. **GetCartItemsUseCase**: Fetches the current list of items in the shopping cart.
2. **AddItemToCartUseCase**: Adds a new item to the cart.

These use cases are connected to the `CartViewModel`, which processes the intents and manages the state of the UI.

---

## MVI Components Explained

### **1. State**

State in MVI represents the UI's current condition. It can be in one of the following states:
- **Loading**: The app is performing an operation (e.g., adding an item to the cart).
- **Success**: The operation is successful, and the data is ready to be displayed.
- **Error**: Something went wrong during the operation.

Here’s an example of the **State** sealed class:

```kotlin
sealed class CartState {
    object Loading : CartState()
    data class Success(val items: List<CartItem>) : CartState()
    data class Error(val message: String) : CartState()
}
```
### **2. Intent**

Intents represent the user’s actions in the app, which trigger state changes in the ViewModel. Each Intent corresponds to a specific user action, like adding an item to the cart.

Here’s an example of the Intent sealed class:

```kotlin
sealed class CartIntent {
    data class AddItem(val item: CartItem) : CartIntent()
    data class RemoveItem(val itemId: String) : CartIntent()
    data class UpdateQuantity(val itemId: String, val quantity: Int) : CartIntent()
}
```
### **3. ViewModel**
The ViewModel is the central place where Intents are processed, and it manages the state updates. It reacts to Intents and updates the state accordingly.

### How to Run
To run this app:

1.Clone this repository.

2.Open the project in Android Studio.

3.Build and run on your preferred Android device or emulator.

### Unit Testing
Unit tests are implemented to ensure the correctness of the business logic. These tests help validate how the app handles adding items to the cart and retrieving the current cart state.

### **3. Screenshot**
<img width="368" alt="MVI" src="https://github.com/user-attachments/assets/d6d1ffdd-4390-475c-9aa5-ae6800c71b42" />

### **4. Screenshot Unit Testing**
<img width="1430" alt="repositoryimpl" src="https://github.com/user-attachments/assets/1fffa100-430d-449a-bfb6-ec22074678ef" />

<img width="1430" alt="vm" src="https://github.com/user-attachments/assets/22eded09-6931-4cbc-9ead-6a37fdde45a7" />
