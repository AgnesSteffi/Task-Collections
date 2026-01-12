package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConcurrentInventoryTest {

    @Test
    void testConcurrentIncrementQuantity() throws InterruptedException {
        ConcurrentInventory inventory = new ConcurrentInventory();

        inventory.addProduct(
                new Product("P100", "Laptop", "Electronics", 80000, 0)
        );

        int threads = 50;
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    inventory.incrementQuantity("P100");
                }
            });
        }

        for (Thread t : workers) t.start();
        for (Thread t : workers) t.join();

        int finalQty = inventory.searchProduct("P100").getQuantity();

        assertEquals(threads * 1000, finalQty);
    }
}
