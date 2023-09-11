package queue;

public class EmptyContainer extends Container {

    public static String emptyError = "Queue is empty";

    @Override
    public Object getCargo() { throw new Error(emptyError);}
}