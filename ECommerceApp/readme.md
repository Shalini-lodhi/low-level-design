# Problem Statement

**Design a Shopping Cart for an E-commerce Website**

- Add, update, or remove items from the cart.
- Handle checkout and payment processing.
- Handle discounts as well.

---

## 1. Flow Diagram

### Steps:
1. User logs into the e-commerce system.
2. User browses products and adds, updates, or removes items in the cart.
3. User views the cart to review items, quantities, and total cost.
4. Discounts are applied (automatically or via promo code).
5. User proceeds to checkout:
   - Shipping details are provided.
   - Payment is processed.
6. Order is placed, and the user receives confirmation.

---

## 2. Objects

- **User**: Represents interaction with the system.
- **Product**: Represents items available for purchase.
- **Cart**: Represents the shopping cart holding the selected products.
- **CartItem**: Represents a specific product in the cart with a quantity.
- **Discount**: Represents the discount logic applied to the cart total.
- **Order**: Represents the order after checkout.
- **Payment**: Handles the payment process.

---

## 3. Class Diagram

```mermaid
classDiagram
    class User {
        +String userId
        +String name
        +String email
        +void login()
        +void logout()
    }

    class Product {
        +String productId
        +String name
        +double price
        +int stock
        +String getProductId()
        +double getPrice()
        +int getStock()
        +void reduceStock(int quantity)
    }

    class CartItem {
        +Product product
        +int quantity
        +double calculatePrice()
        +Product getProduct()
        +int getQuantity()
        +void updateQuantity(int quantity)
    }

    class Cart {
        +String cartId
        +String userId
        +List<CartItem> items
        +double totalAmount
        +void addItem(Product product, int quantity)
        +void removeItem(String productId)
        +void updateItem(String productId, int quantity)
        +void calculateTotal()
        +double getTotalAmount()
    }

    class Discount {
        +String discountId
        +String description
        +double percentage
        +double applyDiscount(double amount)
    }

    class Order {
        +String orderId
        +String userId
        +String cartId
        +void generateOrder()
    }

    class Payment {
        +String paymentId
        +String orderId
        +double amount
        +String status
        +void processPayment()
    }

    %% Relationships
    User --|> Cart : owns
    Product <|-- CartItem : is part of
    CartItem <|-- Cart : contains
    Cart --> Discount : applies
    Cart --> Order : generates
    Order --> Payment : processes
```
