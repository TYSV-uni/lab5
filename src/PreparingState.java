import java.util.concurrent.TimeUnit;

public class PreparingState implements State
{
    @Override
    public void next(Order order)
    {
        System.out.println("Please wait, order is being prepared");
        try
        {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Order prepared, sending");
        try
        {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
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
