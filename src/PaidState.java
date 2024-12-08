public class PaidState implements State
{
    @Override
    public void next(Order order)
    {
        order.state = new PreparingState();
    }

    @Override
    public void prev(Order order)
    {
        order.state = new CreatedState();
    }

    @Override
    public void print_state()
    {
        System.out.println("State of the order: PAID");
    }
}
