package Project;

import java.util.*;

public class Inventory {

    private final HashMap<String, Product> products = new HashMap<>();
    private final HashSet<String> categories = new HashSet<>();

    public boolean addProduct(Product p){
        if (p == null) return false;
        if (p.getProductId() == null || p.getProductId().isBlank())
            return false;
        if (p.getProductName() == null || p.getProductName().isBlank())
            return false;
        if (p.getCategory() == null || p.getCategory().isBlank())
            return false;
        if (p.getPrice() < 0 || p.getQuantity() <= 0)
            return false;
        if(products.containsKey(p.getProductId())) return false;
        products.put(p.getProductId(), p);
        categories.add(p.getCategory());
        return true;
    }

    public boolean removeProduct(String id){
        Product removed = products.remove(id);
        if (removed == null) return false;
        String category = removed.getCategory();
        boolean categoryExists = products.values()
                .stream()
                .anyMatch(p -> p.getCategory().equals(category));

        if (!categoryExists) {
            categories.remove(category);
        }
        return true;
    }

    public Product searchProduct(String productId){
        return products.get(productId);
    }

    public boolean updatePrice(String productId, double newPrice){
        if(!products.containsKey(productId)) return false;
        if(newPrice < 0) return false;
        Product p = products.get(productId);
        p.updatePrice(newPrice);
        return true;
    }

    public boolean updateQuantity(String productId, int newQuantity){
        if(!products.containsKey(productId)) return false;
        if(newQuantity <= 0) return false;
        Product p = products.get(productId);
        p.updateQuantity(newQuantity);
        return true;
    }

    public Collection<Product> listAll(){
        return products.values();
    }

    public Set<String> listCategories(){
        return categories;
    }

}
