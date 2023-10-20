package submarine;

public class CommandM extends Command{
    public CommandM() { symbol = 'm'; }
    @Override
    public void runCommand( Nemo submarine ) {  submarine.throwBomb(); }

}
