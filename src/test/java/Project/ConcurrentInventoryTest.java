package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConcurrentInventoryTest {

    @Test
    void testConcurrentAddAndUpdate() throws InterruptedException {
        ConcurrentInventory inventory = new ConcurrentInventory();

        Product p = new Product("P1", "Laptop", "Electronics", 70000, 10);
        assertTrue(inventory.addProduct(p));

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                inventory.updateQuantity("P1", 50);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Product result = inventory.searchProduct("P1");
        assertNotNull(result);
        assertTrue(result.getQuantity() > 0);
    }
}
