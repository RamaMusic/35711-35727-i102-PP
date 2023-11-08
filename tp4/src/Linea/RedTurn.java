package Linea;

public class RedTurn extends GameManager {

    @Override
    public GameManager playRedAs(int desiredColumn, Linea game) {
        game.stackSlotOn(desiredColumn, Linea.RED_SLOT);
        if (this.isDraw( game )) {
            return new Draw();
        }
        return new BlueTurn();
    }

    @Override
    public GameManager playBlueAs(int desiredColumn, Linea game) {
        throw new RuntimeException("It's not blues's turn!");
    }

    @Override
    public String getStatus() {
        return "It's red's turn!";
    }
}
