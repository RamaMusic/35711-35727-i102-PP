package submarineOLD.submarine;

import submarineOLD.position.Direction;
import submarineOLD.position.Point;

import java.util.HashMap;

public class Nemo {

    private Submarine submarine;

    private HashMap<Character, Runnable> commands = new HashMap<>();

    public Nemo(Point point, Direction direction) {
        submarine = new SubmarineAlive(point, direction);
        createCommands();
    }

    public String getPosition() { return submarine.getPosition(); }

    public Direction getDirection() { return submarine.getDirection(); }

    public int getDepth() { return submarine.getDepth(); }

    public boolean isDead() { return submarine.isDead(); }

    public void throwCapsule() {
        submarine = submarine.throwCapsule();
    }

    private void createCommands() {
        commands.put('d', submarine::descend);
        commands.put('u', submarine::ascend);
        commands.put('l', submarine::turnLeft);
        commands.put('r', submarine::turnRight);
        commands.put('f', submarine::moveForward);
        commands.put('m', this::throwCapsule);
    }

    public void command(String instructions) {
        instructions.chars().forEach(ch -> executeCommand(String.valueOf((char) ch)));
    }
    private void executeCommand(String instruction) {
        commands.get(instruction.charAt(0)).run();
    }


}