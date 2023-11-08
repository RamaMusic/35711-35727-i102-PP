package Linea;

public abstract class Turn {

    public abstract Turn playRedAs(int desiredColumn, Linea game);

    public abstract Turn playBlueAs(int desiredColumn, Linea game);

    public abstract boolean isRedTurn();

    public abstract boolean isBlueTurn();

    public abstract String getStatus();

    public boolean isDraw(Linea game) {
        return game.getBoard().stream().allMatch(column -> column.size() == game.getHeight());
    }

    public boolean equals(Object other) {
        return other.getClass() == this.getClass();
    }
}
