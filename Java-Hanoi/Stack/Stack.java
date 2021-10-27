public interface Stack {
    public boolean isEmpty();
    public boolean push(Plate p);
    public Plate pop() throws EmptyStackException;
    public Plate top() throws EmptyStackException;
    public int size();
    public Plate search();
}
