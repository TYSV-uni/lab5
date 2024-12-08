public interface State
{
    void next(Order order);

    void prev(Order order);

    void print_state();
}
