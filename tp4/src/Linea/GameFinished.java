package Linea;

public class GameFinished extends GameManager {

    private String WINNER;

    public GameFinished(char winner) {
        this.WINNER = winner == Linea.RED_SLOT ? "Red (X)" : "Blue (O)";
    }

    @Override
    public GameManager playRedAs(int desiredColumn, Linea game) { throw new RuntimeException("The game has finished!"); }

    @Override
    public GameManager playBlueAs(int desiredColumn, Linea game) { throw new RuntimeException("The game has finished!"); }

    @Override
    public String getStatus() { return "The game has finished.\nThe winner is: " + this.WINNER; }

    @Override
    public boolean isFinished() { return true; }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() == GameFinished.class) { return this.getStatus().equals(((GameFinished) other).getStatus()); }
        return false;
    }
}
