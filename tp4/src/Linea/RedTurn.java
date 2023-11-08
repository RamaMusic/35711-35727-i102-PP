package Linea;

public class RedTurn extends Turn {

    @Override
    public Turn playRedAs(int desiredColumn, Linea game) {
        game.stackSlotOn(desiredColumn, Linea.RED_SLOT);
        if (this.isDraw( game )) {
            return new Draw();
        }
        return new BlueTurn();
    }

    @Override
    public Turn playBlueAs(int desiredColumn, Linea game) {
        throw new RuntimeException("It's not blues's turn!");
    }

    @Override
    public boolean isRedTurn() {
        return true;
    }

    @Override
    public boolean isBlueTurn() {
        return false;
    }

    @Override
    public String getStatus() {
        return "It's red's turn!";
    }
}
