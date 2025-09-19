import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void addProductToCart(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println(quantity + " " + product.getName() + " added to cart");
                return;
            }
        }

        cartItems.add(new CartItem(product, quantity));
        System.out.println(quantity + " " + product.getName() + " added to cart");
    }

    public void removeProductByIndex(int index) {
        if (index > 0 && index <= cartItems.size()) {
            CartItem removedItem = cartItems.remove(index -1);
            System.out.println(removedItem.getProduct().getName() + "removed from cart");
        } else {
            System.out.println("Invalid number");
        }
    }

    public void showCart() {
        System.out.println("\n========= 🛍️  CART =========\n");
        if (cartItems.isEmpty()) {
            System.out.println(" (empty)");
        } else {
            int i = 1;
            for (CartItem item : cartItems) {
                Product p = item.getProduct();
                int qty = item.getQuantity();
                double totalPrice = p.getPrice() * qty;
                System.out.println(i + ". " + p.getName() + " ( " + qty + " pcs = " + totalPrice + "$)");
                i++;
            }
            System.out.println("\n----------------------------------------");
            System.out.println(" \uD83D\uDCB5 Total: " + calculateTotal() + "$");
        }
        System.out.println("==============================\n");
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clear() {
        cartItems.clear();
    }
}
