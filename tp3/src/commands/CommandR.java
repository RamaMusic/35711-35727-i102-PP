package commands;

import submarine.Nemo;

public class CommandR extends Command{
    public CommandR() { symbol = 'r'; }
    @Override
    public void runCommand( Nemo submarine ) { submarine.turnRight(); }
}
