package queue;

public abstract class Container {
    public static String emptyError = "Queue is empty";

    public abstract Object getCargo();

    public static class FilledContainer extends Container {
        private Object cargo;

        public FilledContainer(Object cargo) { this.cargo = cargo; }

        @Override
        public Object getCargo() { return this.cargo; }
    }

    public static class EmptyContainer extends Container {

        @Override
        public Object getCargo() { throw new Error(emptyError);}
    }
}

