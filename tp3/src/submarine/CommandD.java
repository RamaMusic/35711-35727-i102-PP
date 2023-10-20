package submarine;

public class CommandD extends Command {

    public CommandD() { symbol = 'd'; }

    @Override
    public void runCommand( Nemo submarine ) { submarine.descend(); }

}
