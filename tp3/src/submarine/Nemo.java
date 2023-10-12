package submarine;

import java.util.ArrayList;

public class Nemo {

    private int depth = 0;

    private Direction direction;

    private ArrayList<Integer> position = new ArrayList<Integer>();

    private boolean isAlive = true; // false == explotó

    public Nemo(int x, int y, Direction direction) {
        this.position.add(x);
        this.position.add(y);
        this.direction = direction;
    }

    public Direction getDirection() { return direction; }

    public ArrayList<Integer> getPosition() { return position; }

    public int getDepth() { return depth; }

    public void command(String instructions) {
        instructions.chars().forEach(ch -> executeCommand(String.valueOf((char) ch)));
    }
    private void executeCommand(String instruction) {
        switch (instruction) {
            case "d":
                this.depth--;
                break;
            case "u":
                this.depth++;
                break;
            case "r":
                direction = direction.turnRight();
                break;
            case "l":
                direction = direction.turnLeft();
                break;
            default:
                break;
        }
    }

    public boolean isAlive() { return this.isAlive; }
}