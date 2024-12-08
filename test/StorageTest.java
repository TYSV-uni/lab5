import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;



public class StorageTest
{
    private Storage storage;

    @BeforeEach
    void set_up() {
        storage = new Storage();
    }

    @Test
    void add() {
        Product product1 = new Product("Apple", 100, 10);
        Product product2 = new Product("Apple", 100, 5);
        Product product3 = new Product("Banana", 50, 20);

        storage.add(product1);
        storage.add(product2);
        storage.add(product3);

        assertEquals(2, storage.products.size());
        assertEquals(15, storage.products.get(Product.get_product_index_by_name(storage.products, "Apple")).quantity);

    }

    @Test
    void remove() {
        Product product = new Product("Orange", 50, 20);
        storage.add(product);

        assertTrue(storage.remove("Orange", 10));
        assertEquals(10, storage.products.get(Product.get_product_index_by_name(storage.products, "Orange")).quantity);

        assertFalse(storage.remove("Oregano", 2));

        assertTrue(storage.remove("Orange", 15));
        assertEquals(0, storage.products.size());
    }

    @Test
    void reserve() {
        Product product = new Product("Banana", 30, 25);
        storage.add(product);

        assertTrue(storage.reserve("Banana", 10));
        assertEquals(15, storage.products.get(Product.get_product_index_by_name(storage.products, "Banana")).quantity);
        assertEquals(10, storage.reserves.get(Product.get_product_index_by_name(storage.reserves, "Banana")).quantity);

        assertFalse(storage.reserve("Banana", 20));
        assertFalse(storage.reserve("Gear", 20));
    }

    @Test
    void release() {
        Product product = new Product("Cherry", 20, 10);
        storage.add(product);
        storage.reserve("Cherry", 5);

        assertTrue(storage.release(new Product("Cherry", 20, 3)));
        assertEquals(8, storage.products.get(Product.get_product_index_by_name(storage.products, "Cherry")).quantity);

        assertFalse(storage.release(new Product("Cherry", 20, 3)));
        assertEquals(2, storage.reserves.get(Product.get_product_index_by_name(storage.reserves, "Cherry")).quantity);

        assertTrue(storage.release(new Product("Cherry", 20, 2)));
        assertEquals(0, storage.reserves.size());

    }

    @Test
    void send() {
        Product product = new Product("Pine", 40, 8);
        storage.add(product);
        storage.reserve("Pine", 4);

        assertTrue(storage.send(new Product("Pine", 40, 2)));
        assertEquals(4, storage.products.get(Product.get_product_index_by_name(storage.products, "Pine")).quantity);
        assertEquals(2, storage.reserves.get(Product.get_product_index_by_name(storage.products, "Pine")).quantity);

        assertFalse(storage.send(new Product("Pine", 40, 3)));
    }

    @Test
    void get_price_by_name() {
        Product product = new Product("Grapes", 60, 12);
        storage.add(product);

        assertEquals(60, storage.get_price_by_name("Grapes"));
        assertEquals(-1, storage.get_price_by_name("NonExistentProduct"));
    }
}
