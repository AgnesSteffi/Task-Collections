package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void testAddProduct() {
        Inventory inventory = new Inventory();
        Product p = new Product("P1", "Laptop", "Electronics", 70000, 10);

        assertTrue(inventory.addProduct(p));
    }

    @Test
    void testDuplicateProductId() {
        Inventory inventory = new Inventory();

        Product p1 = new Product("P1", "Laptop", "Electronics", 70000, 10);
        Product p2 = new Product("P1", "Mouse", "Electronics", 500, 20);

        inventory.addProduct(p1);
        assertFalse(inventory.addProduct(p2));
    }

    @Test
    void testRemoveProduct() {
        Inventory inventory = new Inventory();
        Product p = new Product("P1", "Laptop", "Electronics", 70000, 10);

        inventory.addProduct(p);
        assertTrue(inventory.removeProduct("P1"));
        assertNull(inventory.searchProduct("P1"));
    }

    @Test
    void testUpdateQuantity() {
        Inventory inventory = new Inventory();
        Product p = new Product("P1", "Laptop", "Electronics", 70000, 10);

        inventory.addProduct(p);
        assertTrue(inventory.updateQuantity("P1", 20));
        assertEquals(20, inventory.searchProduct("P1").getQuantity());
    }
}
