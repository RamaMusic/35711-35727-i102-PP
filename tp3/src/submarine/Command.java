package submarine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Command {

    private Character symbol;
    private Consumer<Submarine> command;

    public static ArrayList<Command> commands = new ArrayList<Command>(Arrays.asList(
            new Command( 'f', Submarine::moveForward ),
            new Command( 'l', Submarine::turnLeft ),
            new Command( 'r', Submarine::turnRight ),
            new Command( 'd', Submarine::descend ),
            new Command( 'u', Submarine::ascend ),
            new Command( 'm', Submarine::throwBomb )
    ));

    public Command( char symbol, Consumer<Submarine> command ) {
        this.symbol = symbol;
        this.command = command;
    }

    public static Command runnableFor(Character instruction ) {
        return commands.stream()
                .filter( command -> command.isSymbol( instruction ) )
                .findFirst().get();
    }

    public void runCommand( Submarine nemo ) { command.accept( nemo ); }

    private boolean isSymbol( Character instruction ) { return symbol.equals( instruction ); }
}
