package Linea;

public class GameModeB extends GameMode {

    public GameModeB() {
        this.symbol = 'B';
    }

    @Override
    public boolean checkVictory(char slotSymbol, Linea game) {
        return game.checkDiagonalVictory(slotSymbol);
    }
}
