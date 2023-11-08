package LineaOLD;

public class GameModeC extends GameMode {

    public GameModeC() {
        this.symbol = 'C';
    }

    @Override
    public boolean checkVictory(char slotSymbol, Linea game) {
        return checkHorizontalVictory(slotSymbol, game) || checkVerticalVictory(slotSymbol, game) || checkDiagonalVictory(slotSymbol, game);
    }
}
