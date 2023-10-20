package submarine;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {

    protected Character symbol;
    private static ArrayList<Command> commands = new ArrayList<Command>(Arrays.asList(
            new CommandF(),
            new CommandL(),
            new CommandR(),
            new CommandD(),
            new CommandU(),
            new CommandM()
    ));

    public static Command runnableFor( Character instruction ) {
        return commands.stream()
                .filter( command -> command.isSymbol( instruction ) )
                .findFirst().get();
    }

    public abstract void runCommand( Nemo submarine );

    private boolean isSymbol( Character instruction ) { return symbol.equals( instruction ); }
}