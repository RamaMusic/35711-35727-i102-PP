package submarine;

import position.Direction;
import position.Point;

import java.util.HashMap;

public class Nemo {
    public Direction direction;
    public Point position;
    private HashMap <Character, Runnable> commands = new HashMap<>();

    private Boolean isAlive = true;

    public Nemo(Point position, Direction direction){
        this.position = position;
        this.direction = direction;
        createCommands();
    }

    public void command(String instructions) {
        instructions.chars().forEach(ch -> executeCommand(String.valueOf((char) ch)));
    }

    public String getPosition() { return position.toString(); }

    public Direction getDirection() { return direction; }

    public int getDepth() { return position.getZ(); }

    public boolean isAlive() { return isAlive; }

    private void executeCommand(String instruction) {
        commands.get(instruction.charAt(0)).run();
    }
    private void createCommands() {
        commands.put('d', () -> position = position.descend());
        commands.put('u', () -> position = position.ascend());
        commands.put('l', () -> direction = direction.turnLeft());
        commands.put('r', () -> direction = direction.turnRight());
        commands.put('f', ()-> position = direction.move(position));
//        commands.put('m', () -> throwCapsule());
    }
}