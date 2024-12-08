import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest
{
    private Order order;

    @BeforeEach
    void setUp() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 100, 10));
        products.add(new Product("Banana", 50, 5));
        order = new Order(products);
    }

    @Test
    void next_state() {
        order.next_state();
        assertInstanceOf(PaidState.class, order.state);

        order.next_state();
        assertInstanceOf(PreparingState.class, order.state);

        order.next_state();
        assertInstanceOf(SentState.class, order.state);

        order.next_state();
        assertInstanceOf(SentState.class, order.state);
    }

    @Test
    void testPrevState() {
        order.next_state();
        order.next_state();
        order.next_state();

        assertInstanceOf(SentState.class, order.state);

        order.prev_state();
        assertInstanceOf(PreparingState.class, order.state);

        order.prev_state();
        assertInstanceOf(PaidState.class, order.state);

        order.prev_state();
        assertInstanceOf(CreatedState.class, order.state);

        order.prev_state();
        assertInstanceOf(CreatedState.class, order.state);
    }

}
