# Designing a Coffee Vending Machine

### Requirements
1. The coffee vending machine should support different types of coffee, such as espresso, cappuccino, and latte.
2. Each type of coffee should have a specific price and recipe (ingredients and their quantities).
3. The machine should have a menu to display the available coffee options and their prices.
4. Users should be able to select a coffee type and make a payment.
5. The machine should dispense the selected coffee and provide change if necessary.
6. The machine should track the inventory of ingredients and notify when they are running low.
7. The machine should handle multiple user requests concurrently and ensure thread safety.

### Classes, Interfaces and Enumerations
1. The **Coffee** class represents a coffee type with its name, price, and recipe (ingredients and their quantities).
2. The **Ingredient** class represents an ingredient used in making coffee, with its name and quantity. 
   1. It provides a synchronized method to update the quantity.
3. The **Payment** class represents a payment made by a user, with the amount paid.
4. The **CoffeeMachine** class is the main class that manages the coffee vending machine. 
   1. It follows the Singleton pattern to ensure a single instance of the machine.
5. The **CoffeeMachine** class initializes the coffee menu and ingredients in its constructor. 
   1. It provides methods to display the menu, select a coffee, dispense coffee, and update ingredient quantities.
6. The `hasEnoughIngredients` method checks if there are sufficient ingredients to make a selected coffee, while the updateIngredients method updates the ingredient quantities after dispensing a coffee.
7. The **CoffeeVendingMachine** class is the entry point of the application and demonstrates the usage of the coffee vending machine. 
   1. It creates an instance of the machine, displays the menu, and simulates concurrent user requests using an ExecutorService.

### Class Diagram
```mermaid
classDiagram
    class Payment {
        - double amount
        + Payment(double amount)
        + getAmount(): double
    }

    class Ingredient {
        - String name
        - int quantity
        + Ingredient(String name, int quantity)
        + getName(): String
        + getQuantity(): int
        + updateQuantity(int amount)
    }

    class Coffee {
        - String name
        - double price
        - Map~Ingredient, Integer~ recipe
        + Coffee(String name, double price, Map<Ingredient, Integer> recipe)
        + getName(): String
        + getPrice(): double
        + getRecipe(): Map~Ingredient, Integer~
    }

    class CoffeeMachine {
        - static CoffeeMachine instance
        - List<Coffee> coffeeMenu
        - Map<String, Ingredient> ingredients
        + getInstance(): CoffeeMachine
        + displayMenu()
        + selectCoffee(String coffeeName): Coffee
        + dispenseCoffee(Coffee coffee, Payment payment)
    }

    class CoffeeVendingMachineDemo {
        + run()
    }

    CoffeeMachine "1" -- "*" Coffee
    CoffeeMachine "1" -- "*" Ingredient
    Coffee "1" -- "*" Ingredient : uses
    CoffeeMachine "1" -- "1" Payment : processes
    CoffeeVendingMachineDemo "1" -- "1" CoffeeMachine : uses

```

### Skeleton Diagram
```mermaid
classDiagram
    Coffee <|-- Espresson
    Coffee <|-- Latte
    Coffee <|-- Cappuccino

    CoffeeMachine --> Menu
    CoffeeMachine --> Ingrdient
    Ingrdient --> Menu

    Menu --> WarmWater
    Menu --> Coffee
    Menu --> Milk
```