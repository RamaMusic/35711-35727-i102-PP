package LineaOLD;

public class GameModeB extends GameMode {

    public GameModeB() {
        this.symbol = 'B';
    }

    @Override
    public boolean checkVictory(char slotSymbol, Linea game) {
        return checkDiagonalVictory(slotSymbol, game);
    }
}
