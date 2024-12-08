public class Main
{
    public static void main(String[] args)
    {
        Product product1 = new Product("Rope", 60, 2);
        Product product2 = new Product("Soap", 20, 1);
        Product product3 = new Product("Chair", 200, 1);
        //Creating store with default balance of 0 and 0 orders
        Store store = new Store(new Storage());
        //Adding products to the store storage
        store.storage.add(product1);
        store.storage.add(product2);
        store.storage.add(product3);
        //Managing store storage (includes the ability to add a custom product)
        store.manage_storage();
        //Adding orders to the store
        store.add_order();
        store.add_order();
        //Removing or changing the status of order
        store.change_order();
        //Displaying store balance, inventory and active orders
        store.display_info();

    }
}
