public class Product {
    private String name;
    private double price;
    private String description;
    private int stock;

    public Product(String name, double price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
