import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();

        //Default products
        store.addProduct(new Product("Laptop", 12000, "Gaming laptop", 5));
        store.addProduct(new Product("Phone", 8000, "Smartphone", 10));
        store.addProduct(new Product("Headphones", 1500, "Wireless headphones", 20));

        //Default customer
        store.addCustomer(new Customer("Anna", "anna@mail.com", "Bergvägen 28, Stockholm"));

        boolean runMainMenu = true;
        while (runMainMenu) {
            System.out.println("\n========================================");
            System.out.println("🛒  E-COMMERCE SYSTEM");
            System.out.println("========================================");
            System.out.println("1️⃣  Show products");
            System.out.println("2️⃣  Add customer");
            System.out.println("3️⃣  Show customers");
            System.out.println("4️⃣  Shop as customer");
            System.out.println("0️⃣  Exit ❌");
            System.out.println("========================================");
            System.out.print("👉 Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> store.showProducts();
                case 2 -> addCustomerFromUserInput(scanner, store);
                case 3 -> store.showCustomers();
                case 4 -> openCustomerMenu(scanner, store);
                case 0 -> runMainMenu = false;
                default -> System.out.println("wrong choice, try again!");
            }
        }
    }

    private static void openCustomerMenu(Scanner scanner, Store store){
            Customer currentCustomer = null;

            while (currentCustomer == null) {
                if (store.getCustomers().isEmpty()) {
                    System.out.println("No customers registered yet, PLease add a new customer.");
                    currentCustomer = addCustomerFromUserInput(scanner, store);
                } else {
                    /*for (int i = 0; i < store.getCustomers().size(); i++) {
                        System.out.println((i + 1) + ". " + store.getCustomers().get(i).getName());
                    }
                    System.out.println("0. Add new customer");
                    System.out.println("Choose a number: ");*/
                    System.out.println("\n===== 🧑‍🤝‍🧑 SELECT CUSTOMER =====\n");
                    for (int i = 0; i < store.getCustomers().size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + store.getCustomers().get(i).getName());
                    }
                    System.out.println(" 0. ➕ Add new customer");
                    System.out.println("\n==============================");
                    System.out.print("👉 Choose a number: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    if (index == 0) {
                        currentCustomer = addCustomerFromUserInput(scanner, store);
                    } else if (index > 0 && index <= store.getCustomers().size()) {
                        currentCustomer = store.getCustomers().get(index - 1);
                    } else {
                        System.out.println("Invalid choice, try again!");
                    }
                }
            }

            boolean  runShoppingMenu = true;
            while (runShoppingMenu) {
                System.out.println("\n===== 🛒 SHOPPING MENU for " + currentCustomer.getName() + " =====");
                System.out.println(" 1️⃣ Show products");
                System.out.println(" 2️⃣ Add product to cart");
                System.out.println(" 3️⃣ Remove product from cart");
                System.out.println(" 4️⃣ Show cart");
                System.out.println(" 5️⃣ Place order");
                System.out.println(" 6️⃣ Show my orders");
                System.out.println(" 0️⃣ Back to main menu");
                System.out.println("========================================");
                System.out.print("👉 Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> store.showProducts();
                    case 2 -> addProductToCartFromUserInput(scanner, store, currentCustomer);
                    case 3 -> removeProductFromCartFromUserInput(scanner, currentCustomer);
                    case 4 -> currentCustomer.getCart().showCart();
                    case 5 -> placeOrder(store, currentCustomer, scanner);
                    case 6 -> currentCustomer.showOrders();
                    case 0 -> runShoppingMenu = false;
                    default -> System.out.println("Wrong choice, try again!");
                }
            }
    }

    private static Customer addCustomerFromUserInput(Scanner scanner, Store store) {
        System.out.println("\n--- Add new customer ---");
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, email, address);
        store.addCustomer(customer);
        System.out.println("\nWelcome " + name + "!");
        return customer;
    }

    private static void addProductToCartFromUserInput(Scanner scanner, Store store, Customer currentCustomer) {
        store.showProducts();
        System.out.println("Choose product number");
        int productIndex = scanner.nextInt();
        System.out.println("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (productIndex > 0 && productIndex <= store.getProducts().size()) {
            Product selectedProduct = store.getProducts().get(productIndex - 1);
            if (quantity <= selectedProduct.getStock()) {
                currentCustomer.getCart().addProductToCart(selectedProduct, quantity);
            } else {
                System.out.println("Not enough stock available!");
            }
        } else {
            System.out.println("Invalid product number!");
        }
    }

    private static void removeProductFromCartFromUserInput(Scanner scanner, Customer currentCustomer) {
        currentCustomer.getCart().showCart();
        System.out.print("Choose product number to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        currentCustomer.getCart().removeProductByIndex(index);
    }

    private static void placeOrder(Store store, Customer currentCustomer, Scanner scanner) {
        if (currentCustomer.getCart().getCartItems().isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        System.out.println("\n📦 Choose shipping method:");
        System.out.println(" 1️⃣ PostNord");
        System.out.println(" 2️⃣ DHL");
        System.out.println(" 3️⃣ Instabox");
        System.out.print("👉 Your choice: ");
        int shipChoice = scanner.nextInt();
        scanner.nextLine();

        String shippingMethod = switch (shipChoice) {
            case 1 -> "PostNord";
            case 2 -> "DHL";
            case 3 -> "Instabox";
            default -> "PostNord";
        };
        Shipping shipping = new Shipping(shippingMethod);

        System.out.println("\n💳 Choose payment method:");
        System.out.println(" 1️⃣ Credit card");
        System.out.println(" 2️⃣ Swish");
        System.out.println(" 3️⃣ Invoice");
        System.out.print("👉 Your choice: ");
        int payChoice = scanner.nextInt();
        scanner.nextLine();

        String paymentMethod = switch (payChoice) {
            case 1 -> "Credit card";
            case 2 -> "Swish";
            case 3 -> "Invoice";
            default -> "Credit card";
        };
        Payment payment = new Payment(paymentMethod, currentCustomer.getCart().calculateTotal());

        Order order = store.createOrder(currentCustomer, currentCustomer.getCart(), shipping, payment);
        if (order != null) {
            currentCustomer.addOrder(order);
            System.out.println("\n✅ Order placed successfully!");
            System.out.println(" 💰 Total: " + currentCustomer.getCart().calculateTotal() + "$");
            System.out.println(" 📦 Shipping: " + shipping.getDeliveryMethod());
            System.out.println(" 💳 Payment: " + payment.getMethod() + "\n");
            currentCustomer.getCart().clear();
        }
    }
}