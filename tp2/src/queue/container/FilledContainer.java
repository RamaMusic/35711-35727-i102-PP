package queue.container;

public class FilledContainer extends Container {
    private Object cargo;

    public FilledContainer(Object cargo) {
        this.cargo = cargo;
    }

    @Override
    public Object getCargo() {
        return this.cargo;
    }
}
