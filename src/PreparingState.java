public class PreparingState implements State
{
    @Override
    public void next(Order order)
    {
        order.state = new SentState();
    }

    @Override
    public void prev(Order order)
    {
        order.state = new PaidState();
    }

    @Override
    public void print_state()
    {
        System.out.println("State of the order: PREPARING");
    }
}
