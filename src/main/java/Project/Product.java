package Project;

import java.util.Objects;

public class Product {
    private final String productId;
    private final String productName;
    private final String category;
    private double price;
    private int quantity;

    public Product(String productId, String productName, String category, double price, int quantity){
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product p)) return false;
        return Objects.equals(productId, p.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return productId + "|" + productName + "|" + category + "| Rs." + price + "| Qty:" + quantity;
    }
}
