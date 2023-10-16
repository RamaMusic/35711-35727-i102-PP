package submarine;

import position.Depth;
import position.Direction;
import position.Point;

import java.util.HashMap;

public class Nemo {
    public Direction direction;
    public Point position;
    private Depth depth = new Depth();
    private HashMap <Character, Runnable> commands = new HashMap<>();

    public Nemo(Point position, Direction direction){
        // TODO decidir si es necesario tambien pasarle una profundidad.
        this.position = position;
        this.direction = direction;
        createCommands();
    }

    public void command(String instructions) {
        instructions.chars().forEach(ch -> executeCommand(String.valueOf((char) ch)));
    }

    public String getPosition() { return position.toString(); }

    public Direction getDirection() { return direction; }

    public int getDepth() { return depth.getDepth(); }

    private void executeCommand(String instruction) {
        commands.get(instruction.charAt(0)).run();
    }
    private void createCommands() {
        commands.put('d', () -> depth = depth.descend());
        commands.put('u', () -> depth = depth.ascend());
        commands.put('l', () -> direction = direction.turnLeft());
        commands.put('r', () -> direction = direction.turnRight());
        commands.put('f', ()-> position = direction.move(position));
        commands.put('m', () -> depth.throwBomb());
    }
}