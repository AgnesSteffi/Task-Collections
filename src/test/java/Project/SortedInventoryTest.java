package Project;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortedInventoryTest {

    @Test
    void testSortedByQuantity() {
        SortedInventory inventory =
                new SortedInventory(Product.BY_QUANTITY);

        inventory.addProduct(new Product("P1", "Laptop", "Electronics", 70000, 10));
        inventory.addProduct(new Product("P2", "Mouse", "Electronics", 500, 50));
        inventory.addProduct(new Product("P3", "Keyboard", "Electronics", 1500, 20));

        List<Product> list = new ArrayList<>(inventory.listAll());

        assertEquals(10, list.get(0).getQuantity());
        assertEquals(20, list.get(1).getQuantity());
        assertEquals(50, list.get(2).getQuantity());
    }

    @Test
    void testUpdateKeepsSorting() {
        SortedInventory inventory =
                new SortedInventory(Product.BY_PRICE);

        inventory.addProduct(new Product("P1", "Laptop", "Electronics", 70000, 10));
        inventory.addProduct(new Product("P2", "Mouse", "Electronics", 500, 20));

        inventory.updatePrice("P2", 80000);

        List<Product> list = new ArrayList<>(inventory.listAll());
        assertEquals("P1", list.get(0).getProductId());
    }
}
