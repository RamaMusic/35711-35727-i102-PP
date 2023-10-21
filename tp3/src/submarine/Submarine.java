package submarine;

public class Submarine {
    private Direction direction;
    private Point position;
    private DepthState depth = new DepthState();

    public Submarine(Point position, Direction direction ) {
        this.position = position;
        this.direction = direction;
    }

    public void command ( Character instruction ) { this.command( instruction.toString() ); }

    public void command( String instructions ) {
        instructions.chars().forEach( ch -> executeCommand( (char) ch ) );
    }


    public void descend() { depth = depth.descend(); }

    public void ascend() { depth = depth.ascend(); }

    public void turnLeft() { direction = direction.turnLeft();}

    public void turnRight() { direction = direction.turnRight(); }

    public void moveForward() { position = direction.move(position); }

    public void throwBomb() { depth.throwBomb(); }


    private void executeCommand( Character instruction ) {
        Command.runnableFor( instruction ).runCommand( this );
    }


    public Point getPosition() { return position; }

    public Direction getDirection() { return direction; }

    public int getDepth() { return depth.getDepth(); }
}