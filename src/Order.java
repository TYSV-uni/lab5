import java.util.ArrayList;

public class Order
{
    public final ArrayList<Product> products = new ArrayList<>();
    public State state;

    public Order(ArrayList<Product> products)
    {
        this.products.addAll(products);
        this.state = new CreatedState();
    }

    public void next_state()
    {
        state.next(this);
    }

    public void prev_state()
    {
        state.prev(this);
    }

    public void print_state()
    {
        state.print_state();
    }

    static String order_info_to_str(ArrayList<Order> orders)
    {
        if (orders.isEmpty())
            return "No orders";

        StringBuilder x = new StringBuilder();

        for (Order order : orders)
            x.append("\nOrder# ").append(orders.indexOf(order) + 1).append(Product.product_info_to_str(order.products));

        return x.toString();
    }
}
