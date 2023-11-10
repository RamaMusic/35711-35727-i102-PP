package Linea;

public class Draw extends GameManager {
    @Override
    public GameManager playRedAs(int desiredColumn, Linea game) {
        throw new RuntimeException("The game has finished in a Draw!");
    }

    @Override
    public GameManager playBlueAs(int desiredColumn, Linea game) {
        throw new RuntimeException("The game has finished in a Draw!");
    }

    @Override
    public String getStatus() {
        return "The game has finished in a Draw!";
    }

    @Override
    public boolean isFinished() { return true; }

}
