package LineaOLD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class GameMode {

    protected char symbol;

    public static ArrayList<GameMode> gameModes = new ArrayList<GameMode>(Arrays.asList(new GameModeA(), new GameModeB(), new GameModeC()));

    public static GameMode getGamemodeFor( char desiredGameMode ){
            return gameModes.stream()
                    .filter(gameMode -> gameMode.isSymbol(desiredGameMode))
                    .findFirst()
                    .orElseThrow( () -> new RuntimeException("Gamemode must be A, B or C") );
    }

    public boolean isSymbol( char instruction ) { return symbol == instruction; }


    protected boolean checkHorizontalVictory(char slotSymbol, Linea game) {
        return IntStream.range(0, game.getHeight())
                .anyMatch(row -> IntStream.range(0, game.getBase() - 3)
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> game.getBoard().get(row).get(column + i) == slotSymbol)));
    }

    protected boolean checkVerticalVictory(char slotSymbol, Linea game) {
        return IntStream.range(0, game.getBase())
                .anyMatch(column -> IntStream.range(0, game.getHeight() - 3)
                        .anyMatch(row -> IntStream.range(0, 4)
                                .allMatch(i -> game.getBoard().get(row + i).get(column) == slotSymbol)));
    }

    protected boolean checkDiagonalVictory(char slotSymbol, Linea game) {
        return IntStream.range(0, game.getHeight() - 3)
                .anyMatch(row -> IntStream.range(0, game.getBase() - 3)
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> game.getBoard().get(row + i).get(column + i) == slotSymbol)))
                || IntStream.range(0, game.getHeight() - 3)
                .anyMatch(row -> IntStream.range(3, game.getBase())
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> game.getBoard().get(row + i).get(column - i) == slotSymbol)));
    }

    public abstract boolean checkVictory(char slotSymbol, Linea game);
}
