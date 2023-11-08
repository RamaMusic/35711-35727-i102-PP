package Linea;

public class GameFinished extends GameManager {

    private String WINNER;

    public GameFinished(char winner) {
        this.WINNER = winner == Linea.RED_SLOT ? "Red" : "Blue"; // TODO ¿Sacar este if?. Está bien que turno sepa quién ganó?
    }

    @Override
    public GameManager playRedAs(int desiredColumn, Linea game) { throw new RuntimeException("The game has ended!"); }

    @Override
    public GameManager playBlueAs(int desiredColumn, Linea game) { throw new RuntimeException("The game has ended!"); }

    @Override
    public String getStatus() { return "The game has finished.\nThe winner is: " + this.WINNER; }

    @Override
    public boolean isFinished() { return true; }
}
