package submarine;

import depthLevels.DepthState;
import position.Direction;
import position.Point;
import commands.Command;


import java.util.HashMap;

public class Nemo {

    private Direction direction;
    private Point position;
    private DepthState depth = new DepthState();

    public Nemo( Point position, Direction direction ) {
        this.position = position;
        this.direction = direction;
    }

    // TODO lo dejo anotado acá. Sacar todos los packages y dejar un solo package con todas las clases adentro.

    public void command ( Character instruction ) { this.command( instruction.toString() ); }

    public void command( String instructions ) {
        instructions.toLowerCase().chars().forEach( ch -> executeCommand( (char) ch ) );
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