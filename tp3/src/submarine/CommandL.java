package submarine;

public class CommandL extends Command{
    public CommandL() { symbol = 'l'; }
    @Override
    public void runCommand( Nemo submarine ) { submarine.turnLeft(); }
}