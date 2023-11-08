package Linea;

public class Draw extends Turn {
    @Override
    public Turn playRedAs(int desiredColumn, Linea game) {
        throw new RuntimeException("The game has ended in a Draw!");
    }

    @Override
    public Turn playBlueAs(int desiredColumn, Linea game) {
        throw new RuntimeException("The game has ended in a Draw!");
    }

    @Override
    public boolean isRedTurn() {
        return false;
    }

    @Override
    public boolean isBlueTurn() {
        return false;
    }

    @Override
    public String getStatus() {
        return "The game has ended in a Draw!";
    }

    @Override
    public boolean isFinished() { return true; }
}
