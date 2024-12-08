import java.util.ArrayList;

public class Storage
{
    public final ArrayList<Product> products = new ArrayList<>();
    public final ArrayList<Product> reserves = new ArrayList<>();

    public void add(Product product)
    {
        int index = Product.get_product_index_by_name(products, product.name);
        if (index == -1)
            products.add(product);
        else
            products.get(index).quantity += product.quantity;
    }

    private void add_to_arr(ArrayList<Product> products, Product product)
    {
        int index = Product.get_product_index_by_name(products, product.name);
        if (index == -1)
            products.add(product);
        else
            products.get(index).quantity += product.quantity;

    }

    private void remove_from_arr(ArrayList<Product> products, String name, int quantity)
    {
        int index = Product.get_product_index_by_name(products, name);

        if (index == -1)
            return;

        products.get(index).quantity -= quantity;

        if (products.get(index).quantity <= 0)
            products.remove(index);

    }

    public boolean remove(String name, int quantity)
    {
        int index = Product.get_product_index_by_name(products, name);
        if (index == -1)
            return false;

        products.get(index).quantity -= quantity;

        if (products.get(index).quantity <= 0)
            products.remove(index);

        return true;
    }

    private boolean check_availability(String name, int quantity)
    {
        int index = Product.get_product_index_by_name(products, name);
        if (index == -1)
            return false;

        return products.get(index).quantity >= quantity;
    }

    public boolean reserve(String name, int quantity)
    {
        if (!check_availability(name, quantity))
            return false;

        add_to_arr(reserves, new Product(name, products.get(Product.get_product_index_by_name(products, name)).price, quantity));
        remove_from_arr(products, name, quantity);
        return true;
    }

    public boolean release(Product product)
    {
        int index = Product.get_product_index_by_name(reserves, product.name);
        if (index == -1 || reserves.get(index).quantity < product.quantity)
            return false;

        add_to_arr(products, new Product(product.name, product.price, product.quantity));
        remove_from_arr(reserves, product.name, product.quantity);
        return true;
    }

    public boolean send(Product product)
    {
        int index = Product.get_product_index_by_name(reserves, product.name);
        if (index == -1 || reserves.get(index).quantity < product.quantity)
            return false;

        remove_from_arr(reserves, product.name, product.quantity);
        return true;
    }

    public int get_price_by_name(String name)
    {
        if (!check_availability(name, 1))
            return -1;

        int index = Product.get_product_index_by_name(products, name);
        return products.get(index).price;
    }

    public String products_info_to_str()
    {
        return Product.product_info_to_str(products);
    }

}
