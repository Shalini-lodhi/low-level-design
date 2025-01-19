import java.util.*;

// User Class
class User {
    private String userId;
    private String name;
    private String email;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public void login() {
        System.out.println(name + " logged in.");
    }

    public void logout() {
        System.out.println(name + " logged out.");
    }
}

// Product Class
class Product {
    private String productId;
    private String name;
    private double price;
    private int stock;

    public Product(String productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    //to reduce stock
    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }
}

// CartItem Class
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    //total price
    public double calculatePrice() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    //to update the quantity
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Cart Class
class Cart {
    private String cartId;
    private String userId;
    private List<CartItem> items = new ArrayList<>();
    private double totalAmount;

    public Cart(String cartId, String userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
        calculateTotal();
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getProductId().equals(productId));
        calculateTotal();
    }

    public void updateItem(String productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.updateQuantity(quantity);
                break;
            }
        }
        calculateTotal();
    }

    public void calculateTotal() {
        totalAmount = items.stream().mapToDouble(CartItem::calculatePrice).sum();
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

// Discount Class
class Discount {
    private String discountId;
    private String description;
    private double percentage;

    public Discount(String discountId, String description, double percentage) {
        this.discountId = discountId;
        this.description = description;
        this.percentage = percentage;
    }

    public double applyDiscount(double amount) {
        return amount - (amount * percentage / 100);
    }
}

// Order Class
class Order {
    private String orderId;
    private String userId;
    private String cartId;

    public Order(String orderId, String userId, String cartId) {
        this.orderId = orderId;
        this.userId = userId;
        this.cartId = cartId;
    }

    public void generateOrder() {
        System.out.println("Order generated successfully!");
    }
}

// Payment Class
class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private String status;

    public Payment(String paymentId, String orderId, double amount) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
    }

    public void processPayment() {
        System.out.println("Payment processed for amount: " + amount);
        this.status = "Success";
    }
}

// Main Class to Test
public class ECommerceApp {
    public static void main(String[] args) {
        // Create user
        User user = new User("U1", "Alice", "alice@example.com");
        user.login();

        // Create products
        Product product1 = new Product("P1", "Laptop", 1500.00, 10);
        Product product2 = new Product("P2", "Mouse", 50.00, 50);

        // Create cart and add items
        Cart cart = new Cart("C1", "U1");
        cart.addItem(product1, 1);
        cart.addItem(product2, 2);

        System.out.println("Cart Total: " + cart.getTotalAmount());

        // Apply discount
        Discount discount = new Discount("D1", "New Year Offer", 10);
        double discountedTotal = discount.applyDiscount(cart.getTotalAmount());
        System.out.println("Discounted Total: " + discountedTotal);

        // Checkout and generate order
        Order order = new Order("O1", "U1", "C1");
        order.generateOrder();

        // Process payment
        Payment payment = new Payment("P1", "O1", discountedTotal);
        payment.processPayment();

        user.logout();
    }
}
