package submarine;

public class CommandF extends Command{

    public CommandF() { symbol = 'f'; }

    @Override
    public void runCommand( Nemo submarine ) { submarine.moveForward(); }

}
