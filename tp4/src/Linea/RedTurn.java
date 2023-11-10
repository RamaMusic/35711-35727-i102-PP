package Linea;

public class RedTurn extends GameManager {

    @Override
    public GameManager playRedAs(int desiredColumn, Linea game) {
        game.stackSlotOn(desiredColumn, Linea.RED_SLOT);
        return this.isDraw(game) ? new Draw() : new BlueTurn();
    }

    @Override
    public GameManager playBlueAs(int desiredColumn, Linea game) {
        throw new RuntimeException("It's not blue's turn!");
    }

    @Override
    public String getStatus() {
        return "It's red's turn!";
    }
}
