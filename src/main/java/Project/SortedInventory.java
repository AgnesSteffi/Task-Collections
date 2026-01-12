package Project;

import java.util.*;

public class SortedInventory {

    private final TreeMap<String, Product> products = new TreeMap<>();
    private final Set<String> categories = new HashSet<>();
    private Comparator<Product> currentComparator;

    public SortedInventory(Comparator<Product> comparator) {
        this.currentComparator = comparator;
    }

    public boolean addProduct(Product p) {
        if (p == null) return false;
        if (p.getProductId() == null || p.getProductId().isBlank()) return false;
        if (p.getProductName() == null || p.getProductName().isBlank()) return false;
        if (p.getCategory() == null || p.getCategory().isBlank()) return false;
        if (p.getPrice() < 0 || p.getQuantity() <= 0) return false;

        if (products.containsKey(p.getProductId())) return false;

        products.put(p.getProductId(), p);
        categories.add(p.getCategory());
        return true;
    }

    public boolean removeProduct(String productId) {
        Product removed = products.remove(productId);
        if (removed == null) return false;

        String category = removed.getCategory();
        boolean stillExists = products.values()
                .stream()
                .anyMatch(p -> p.getCategory().equals(category));

        if (!stillExists) {
            categories.remove(category);
        }
        return true;
    }

    public Product searchProduct(String productId) {
        return products.get(productId);
    }

    public boolean updatePrice(String productId, double newPrice) {
        if (newPrice < 0) return false;
        Product p = products.get(productId);
        if (p == null) return false;
        p.updatePrice(newPrice);
        return true;
    }

    public boolean updateQuantity(String productId, int newQuantity) {
        if (newQuantity <= 0) return false;
        Product p = products.get(productId);
        if (p == null) return false;
        p.updateQuantity(newQuantity);
        return true;
    }

    public List<Product> listAll() {
        return CollectionUtils.sort(products.values(), currentComparator);
    }

    public Set<String> listCategories() {
        return categories;
    }

    public void changeSorting(Comparator<Product> newComparator) {
        this.currentComparator = newComparator;
    }
}
