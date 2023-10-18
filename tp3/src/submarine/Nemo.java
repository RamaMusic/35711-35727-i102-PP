package submarine;

import depthLevels.DepthState;
import position.Direction;
import position.Point;

import java.util.HashMap;

public class Nemo {
    private Direction direction;
    private Point position;
    private DepthState depth = new DepthState();
    private Boolean bombThrown = false;
    private HashMap <Character, Runnable> commands = new HashMap<>();

    public Nemo(Point position, Direction direction){
        this.position = position;
        this.direction = direction;
        createCommands();
    }

    // TODO lo dejo anotado acá. Sacar todos los packages y dejar un solo package con todas las clases adentro.

    public void command (Character instruction) {
        this.command(instruction.toString());
    } // This should fix the issues when sending the commands with '' instead of ""

    public void command(String instructions) {
        instructions.toLowerCase().chars().forEach(ch -> executeCommand(String.valueOf((char) ch)));
    } // Add extra tests to show that it accepts both lowercase and uppercase. Also Strings and chars.

    public Point getPosition() { return position; }

    public Direction getDirection() { return direction; }

    public int getDepth() { return depth.getDepth(); }

    public boolean hasThrownBomb() { return bombThrown; } // TODO Cambiarle el nombre a estas variables.

    private void executeCommand(String instruction) {
        commands.get(instruction.charAt(0)).run();
    }  // TODO Cambiar el hashmap a diferentes subclases y un filter.
    private void createCommands() {
        commands.put('d', () -> depth = depth.descend());
        commands.put('u', () -> depth = depth.ascend());
        commands.put('l', () -> direction = direction.turnLeft());
        commands.put('r', () -> direction = direction.turnRight());
        commands.put('f', ()-> position = direction.move(position));
        commands.put('m', () -> bombThrown = depth.throwBomb());
    }
}