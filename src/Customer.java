import java.util.ArrayList;

public class Customer {
    private String name;
    private String email;
    private String address;
    private ShoppingCart cart;
    private ArrayList<Order> orders;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.cart = new ShoppingCart();
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void showOrders() {
        System.out.println("\n===== 📑 ORDERS for " + name + " =====\n");
        if (orders.isEmpty()) {
            System.out.println(" (no orders placed yet)");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("Order " + (i + 1) + ":");
                System.out.println(orders.get(i));
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("\n==============================\n");
    }
}
