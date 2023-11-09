package Linea;

public class BlueTurn extends GameManager {
    @Override
    public GameManager playRedAs(int desiredColumn, Linea game) {
        throw new RuntimeException("It's not red's turn!");
    }

    @Override
    public GameManager playBlueAs(int desiredColumn, Linea game) {
        game.stackSlotOn(desiredColumn, Linea.BLUE_SLOT);
        return this.isDraw(game) ? new Draw() : new RedTurn();
    }

    @Override
    public String getStatus() {
        return "It's blue's turn!";
    }
}
