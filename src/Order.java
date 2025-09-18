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
        return "Order{" +
                "status='" + status + '\'' +
                ", shipping=" + shipping.getDeliveryMethod() +
                ", payment=" + payment.getMethod() +
                ", total=" + payment.getTotal() +
                " $}";
    }
}
