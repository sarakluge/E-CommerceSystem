public class Payment {
    private String method;
    private double total;

    public Payment(String method, double total) {
        this.method = method;
        this.total = total;
    }

    public String getMethod() {
        return method;
    }

    public double getTotal() {
        return total;
    }
}
