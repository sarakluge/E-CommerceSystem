import java.util.List;

public class Order {
    private Customer customer;
    private List<CartItem> items;
    private Shipping shipping;
    private Payment payment;
    private String status;

    public Order(Customer customer, List<CartItem> items, Shipping shipping, Payment payment) {
        this.customer = customer;
        this.items = items;
        this.shipping = shipping;
        this.payment = payment;
        this.status = "Created";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Status: ").append(status).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Shipping: ").append(shipping.getDeliveryMethod()).append("\n");
        sb.append("Payment: ").append(payment.getMethod()).append("\n");
        sb.append("Items:\n");
        for (CartItem item : items) {
            sb.append("  - ").append(item).append("\n");
        }
        sb.append("💰 Total: ").append(payment.getTotal()).append("$");
        return sb.toString();
    }
}
