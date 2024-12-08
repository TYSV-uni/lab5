public class CreatedState implements State
{
    @Override
    public void next(Order order)
    {
        order.state = new PaidState();
    }

    @Override
    public void prev(Order order)
    {
        System.out.println("Already root state");
    }

    @Override
    public void print_state()
    {
        System.out.println("State of the order: CREATED");
    }

}
