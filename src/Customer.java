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
        if (orders.isEmpty()) {
            System.out.println(name + " has no orders yet.");
        } else {
            System.out.println("\n--- " + name + "'s orders ---");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + orders.get(i));
            }
        }
    }
}
