package Project;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentInventory {

    private final ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();
    private final Set<String> categories = ConcurrentHashMap.newKeySet();

    public boolean addProduct(Product p){
        if(p == null) return false;
        if(p.getProductId() == null || p.getProductId().isBlank()) return false;
        if(p.getProductName() == null || p.getProductName().isBlank()) return false;
        if(p.getCategory() == null || p.getCategory().isBlank()) return false;
        if(p.getPrice() < 0 || p.getQuantity() < 0) return false;

        Product exist = products.putIfAbsent(p.getProductId(), p);
        if(exist != null ) return false;
        categories.add(p.getCategory());

        return true;
    }

    public boolean removeProduct(String productId){
        Product removed = products.remove(productId);
        if(removed == null) return false;
        String category = removed.getCategory();
        boolean categoryExist = products.values()
                .stream()
                .anyMatch(p -> p.getCategory().equals(category));
        if(!categoryExist) categories.remove(category);
        return true;
    }

    public Product searchProduct(String productId){
        return products.get(productId);
    }

    public boolean updatePrice(String productId, double newPrice){
        if(newPrice < 0) return false;
        return products.computeIfPresent(productId, (k, v) -> {
            v.updatePrice(newPrice);
            return v;
        }) != null;
    }

    public boolean updateQuantity(String productId, int newQuantity){
        if(newQuantity <= 0) return false;
        return products.computeIfPresent(productId, (k, v) -> {
            v.updateQuantity(newQuantity);
            return v;
        }) != null;
    }

    public Collection<Product> listAll(){
        return products.values();
    }

    public Set<String> listCategories(){
        return categories;
    }

    public void incrementQuantity(String productId) {
        products.computeIfPresent(productId, (k, v) -> {
            v.updateQuantity(v.getQuantity() + 1);
            return v;
        });
    }
}
