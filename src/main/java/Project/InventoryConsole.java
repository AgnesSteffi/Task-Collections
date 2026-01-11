package Project;

import java.util.Scanner;

public class InventoryConsole {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nINVENTORY MANAGEMENT");
            System.out.println("1. Add Product");
            System.out.println("2. Search a Product");
            System.out.println("3. Remove a Product");
            System.out.println("4. Update Price");
            System.out.println("5. Update Quantity");
            System.out.println("6. List All Products");
            System.out.println("7. List Categories");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();

                    System.out.print("Product Name: ");
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
                                    ? "Product added successfully"
                                    : "Invalid product or duplicate ID"
                    );
                }

                case 2 -> {
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();

                    Product p = inventory.searchProduct(id);
                    System.out.println(p != null ? p : "Product not found");
                }

                case 3 -> {
                    System.out.print("Enter Product ID to remove: ");
                    String id = sc.nextLine();

                    System.out.println(
                            inventory.removeProduct(id)
                                    ? "Product removed successfully"
                                    : "Product not found"
                    );
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

                case 6 -> {
                    if (inventory.listAll().isEmpty())
                        System.out.println("Inventory is empty");
                    else
                        inventory.listAll().forEach(System.out::println);
                }

                case 7 -> {
                    System.out.println("Categories:");
                    inventory.listCategories().forEach(System.out::println);
                }

                case 0 -> System.out.println("Exiting application...");

                default -> System.out.println("Invalid choice");
            }

        } while (choice != 0);

        sc.close();
    }
}
