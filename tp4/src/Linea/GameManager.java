package Linea;

public abstract class GameManager {

    public abstract GameManager playRedAs(int desiredColumn, Linea game);

    public abstract GameManager playBlueAs(int desiredColumn, Linea game);

    public abstract String getStatus();

    public boolean isDraw(Linea game) {
        return game.getBoard().stream().allMatch(column -> column.size() == game.getHeight());
    }

    public boolean equals(Object other) {
        return other.getClass() == this.getClass();
    }

    public boolean isFinished() {
        return false;
    }
}
