public class SentState implements State
{
    @Override
    public void next(Order order)
    {
        System.out.println("Already final state");
    }

    @Override
    public void prev(Order order)
    {
        order.state = new PreparingState();
    }

    @Override
    public void print_state()
    {
        System.out.println("State of the order: SENT");
    }
}
