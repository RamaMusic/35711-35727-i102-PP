package Linea;

public class BlueTurn extends Turn {
    @Override
    public Turn playRedAs(int desiredColumn, Linea game) {
        throw new RuntimeException("It's not red's turn!");
    }

    @Override
    public Turn playBlueAs(int desiredColumn, Linea game) {
        game.stackSlotOn(desiredColumn, Linea.BLUE_SLOT);
        if (this.isDraw( game )) {
            return new Draw();
        }
        return new RedTurn();
    }

    @Override
    public boolean isRedTurn() {
        return false;
    }

    @Override
    public boolean isBlueTurn() {
        return true;
    }

    @Override
    public String getStatus() {
        return "It's blue's turn!";
    }
}
