import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest
{
    private ArrayList<Product> products;

    @BeforeEach
    void setUp()
    {
        products = new ArrayList<>();
        products.add(new Product("Apple", 100, 10));
        products.add(new Product("Banana", 50, 5));
        products.add(new Product("Orange", 70, 8));
    }

    @Test
    void get_product_index_by_name()
    {
        assertEquals(0, Product.get_product_index_by_name(products, "Apple"));
        assertEquals(1, Product.get_product_index_by_name(products, "Banana"));
        assertEquals(2, Product.get_product_index_by_name(products, "Orange"));
        assertEquals(-1, Product.get_product_index_by_name(products, "NonExistentProduct"));
    }
}
