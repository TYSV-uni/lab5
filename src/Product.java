import java.util.ArrayList;

public class Product
{
    public String name;
    public int price;
    public int quantity;

    public Product(String name, int price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static int get_product_index_by_name(ArrayList<Product> products, String name)
    {
        int index = -1;
        for (Product p : products)
            if (p.name.equals(name))
                index = products.indexOf(p);

        return index;
    }

    public String get_product_info()
    {
        return ("\n\tName: " + name + "\n\tPrice: " + price + "\n\tQuantity: " + quantity);
    }

    static String product_info_to_str(ArrayList<Product> products)
    {
        if (products.isEmpty())
            return "No products";

        StringBuilder x = new StringBuilder();

        for (Product p : products)
            x.append(p.get_product_info()).append("\n");

        return x.toString();
    }
}
