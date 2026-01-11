package Project;

import java.util.Comparator;
import java.util.Scanner;

public class SortedInventoryConsole {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose sorting order:");
        System.out.println("1. By ID");
        System.out.println("2. By Name");
        System.out.println("3. By Quantity");
        System.out.println("4. By Price");
        System.out.print("Choice: ");

        int sortChoice = sc.nextInt();
        sc.nextLine();

        SortedInventory inventory = switch (sortChoice) {
            case 1 -> new SortedInventory(Product.BY_ID);
            case 2 -> new SortedInventory(Product.BY_NAME);
            case 3 -> new SortedInventory(Product.BY_QUANTITY);
            case 4 -> new SortedInventory(Product.BY_PRICE);
            default -> {
                System.out.println("Invalid choice. Defaulting to ID.");
                yield new SortedInventory(Product.BY_ID);
            }
        };

        int choice;

        do {
            System.out.println("\nSORTED INVENTORY");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Search Product");
            System.out.println("4. Update Price");
            System.out.println("5. Update Quantity");
            System.out.println("6. List All Products (Sorted)");
            System.out.println("7. List Categories");
            System.out.println("8. Change sorting order");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    Product p = new Product(id, name, category, price, qty);

                    System.out.println(
                            inventory.addProduct(p)
                                    ? "Product added"
                                    : "Failed to add product"
                    );
                }

                case 2 -> {
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();
                    System.out.println(
                            inventory.removeProduct(id)
                                    ? "Product removed"
                                    : "Product not found"
                    );
                }

                case 3 -> {
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();
                    Product p = inventory.searchProduct(id);
                    System.out.println(p != null ? p : "Product not found");
                }

                case 4 -> {
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("New Price: ");
                    double price = sc.nextDouble();

                    System.out.println(
                            inventory.updatePrice(id, price)
                                    ? "Price updated"
                                    : "Update failed"
                    );
                }

                case 5 -> {
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("New Quantity: ");
                    int qty = sc.nextInt();

                    System.out.println(
                            inventory.updateQuantity(id, qty)
                                    ? "Quantity updated"
                                    : "Update failed"
                    );
                }

                case 6 -> inventory.listAll().forEach(System.out::println);

                case 7 -> inventory.listCategories().forEach(System.out::println);

                case 8 -> {
                    System.out.println("Change sorting to:");
                    System.out.println("1. By ID");
                    System.out.println("2. By Name");
                    System.out.println("3. By Quantity");
                    System.out.println("4. By Price");
                    System.out.print("Choice: ");

                    int newSort = sc.nextInt();
                    sc.nextLine();

                    Comparator<Product> comparator = switch (newSort) {
                        case 1 -> Product.BY_ID;
                        case 2 -> Product.BY_NAME;
                        case 3 -> Product.BY_QUANTITY;
                        case 4 -> Product.BY_PRICE;
                        default -> null;
                    };

                    if (comparator == null) {
                        System.out.println("Invalid choice");
                        break;
                    }

                    inventory.changeSorting(comparator);
                    System.out.println("Sorting order updated successfully!");
                }

                case 0 -> System.out.println("Exiting Sorted Inventory...");

                default -> System.out.println("Invalid choice");
            }

        } while (choice != 0);

        sc.close();
    }
}
