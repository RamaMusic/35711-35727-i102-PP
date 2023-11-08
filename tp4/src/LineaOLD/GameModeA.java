package LineaOLD;

public class GameModeA extends GameMode {

    public GameModeA() {
        this.symbol = 'A';
    }

    @Override
    public boolean checkVictory(char slotSymbol, Linea game) {
        return checkHorizontalVictory(slotSymbol, game) || checkVerticalVictory(slotSymbol, game);
    }
}
