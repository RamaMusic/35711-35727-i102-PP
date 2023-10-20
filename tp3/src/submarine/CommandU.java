package submarine;

public class CommandU extends Command{
    public CommandU() { symbol = 'u'; }
    @Override
    public void runCommand( Nemo submarine ) { submarine.ascend(); }
}
