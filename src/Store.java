import java.util.ArrayList;

public class Store
{
    public Storage storage = new Storage();
    private int balance = 0;
    private final ArrayList<Order> orders = new ArrayList<>();


    public void add_order()
    {
        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Available products:");
        System.out.println(storage.products_info_to_str());

        System.out.println("\nYou are currently adding an order");
        while (true)
        {
            String name = GetInput.get_string("\n\tEnter the product name (0 - exit): ");
            if (name.equals("0"))
                break;

            int quantity = GetInput.get_int_in_range("\tEnter the amount: ", 1, Integer.MAX_VALUE);
            int price = storage.get_price_by_name(name);

            if (!storage.reserve(name, quantity))
                System.out.println("Currently, the requested product or amount is not available");
            else
            {
                products.add(new Product(name, price, quantity));
                System.out.println("Product added to the current order");
            }
        }

        if(products.isEmpty())
            return;

        orders.add(new Order(products));
    }

    public void change_order()
    {
        if (orders.isEmpty())
        {
            System.out.println("There are no orders to change");
            return;
        }

        System.out.println("Current orders:");
        System.out.println(Order.order_info_to_str(orders));

        int index = GetInput.get_int_in_range("Enter the order number (0 - exit): ", 0, orders.size());

        if (index == 0)
            return;

        Order order = orders.get(index - 1);

        switch (GetInput.get_int_in_range("""
                1. Pay the order
                2. Remove the order
                0. Exit
                Enter here:\s""", 0, 2))
        {
            case 0:
                return;

            case 1:
                //Sets Paid state
                order.next_state();
                order.print_state();

                //Sets Preparing state
                order.next_state();

                //Sets Sent state
                order.next_state();
                order.print_state();

                //Actually sends the products
                for (Product p : order.products)
                {
                    storage.send(p);
                    balance += p.price * p.quantity;
                }

                //Removes the order since it has been completed
                orders.remove(order);
                break;

            case 2:
                for (Product p : order.products)
                    storage.release(p);

                orders.remove(order);
                break;

            default:
                System.out.println("Should be impossible to get here");
                break;
        }

    }

    public void display_info()
    {
        System.out.println("\nCurrent store balance: " + balance + " UAH\n");

        System.out.println("Available products:");
        System.out.println(storage.products_info_to_str());

        System.out.println("Current orders:");
        System.out.println(Order.order_info_to_str(orders));

    }

    public void manage_storage()
    {
        String name;
        int price;
        int quantity;
        switch (GetInput.get_int_in_range("""
                1. Add a custom product
                2. Remove a product
                0. Exit
                Enter here:\s""", 0, 2))
        {
            case 0:
                return;

            case 1:
                name = GetInput.get_string("Enter the product name: ");
                price = GetInput.get_int_in_range("Enter the price: ", 0, Integer.MAX_VALUE);
                quantity = GetInput.get_int_in_range("Enter the amount: ", 1, Integer.MAX_VALUE);
                storage.add(new Product(name, price, quantity));
                break;

            case 2:
                System.out.println("Available products:");
                System.out.println(storage.products_info_to_str());
                while (true)
                {
                    name = GetInput.get_string("Enter the product name (0 - exit): ");
                    if (name.equals("0"))
                        break;

                    quantity = GetInput.get_int_in_range("Enter the amount: ", 1, Integer.MAX_VALUE);

                    if (!storage.remove(name, quantity))
                        System.out.println("Product not found");
                    else
                        System.out.println("Product removed");
                }
                break;


            default:
                System.out.println("Should be impossible to get here");
                break;

        }

    }
}
