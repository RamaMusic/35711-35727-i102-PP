package Linea;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GameMode {
    protected char symbol;

    public static ArrayList<GameMode> gameModes = new ArrayList<GameMode>(Arrays.asList(new GameModeA(), new GameModeB(), new GameModeC()));

    public static GameMode getGamemodeFor( char desiredGameMode ){
            return gameModes.stream()
                    .filter(gameMode -> gameMode.isSymbol(desiredGameMode))
                    .findFirst()
                    .orElseThrow( () -> new RuntimeException("Gamemode must be A, B or C.") );
    }

    public boolean isSymbol( char instruction ) { return symbol == instruction; }

   public abstract boolean checkVictory(char slotSymbol, Linea game);
}
