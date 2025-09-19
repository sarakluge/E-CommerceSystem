import java.util.ArrayList;

public class Store {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;

    public Store() {
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void showProducts() {
        System.out.println("\n===== 📦 PRODUCT CATALOG =====\n");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println(" " + (i + 1) + ". " + product.getName() + " - " + product.getPrice() + "$ (" + product.getStock() + " in stock)");
        }
        System.out.println("\n==============================\n");
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void showCustomers() {
        System.out.println("\n===== 🧑‍🤝‍🧑 REGISTERED CUSTOMERS =====\n");

        if (customers.isEmpty()) {
            System.out.println(" (no customers registered yet)");
        } else {
            for (int i = 0; i < customers.size(); i++) {
                Customer c = customers.get(i);
                System.out.println(" " + (i + 1) + ". " + c.getName());
                System.out.println("    📧 Email: " + c.getEmail());
                System.out.println("    🏠 Address: " + c.getAddress());
                System.out.println(" ----------------------------------------");
            }
        }
        System.out.println("\n==============================\n");
    }

    public Order createOrder(Customer customer, ShoppingCart cart, Shipping shipping, Payment payment) {
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Cart is empty, cannot create order.");
            return null;
        }

        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            int qty = cartItem.getQuantity();
            if (product.getStock() < qty) {
                System.out.println("Not enough stock for " + product.getName());
                return null;
            }
            product.setStock(product.getStock() - qty);
        }

        Order order = new Order(customer, new ArrayList<>(cart.getCartItems()), shipping, payment);
        order.setStatus("Paid");
        return order;
    }
}
