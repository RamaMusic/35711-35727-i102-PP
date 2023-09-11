package queue.container;

public class EmptyContainer extends Container {

    @Override
    public Object getCargo() {
        throw new Error(this.emptyError);
    }
}
