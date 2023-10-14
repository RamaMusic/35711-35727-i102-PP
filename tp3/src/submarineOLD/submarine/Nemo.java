package submarineOLD.submarine;

import java.util.ArrayList;

public class Nemo {
    private Submarine submarine;

    public Nemo(int x, int y, Direction direction) {
        submarine = new SubmarineAlive(x, y, direction);
    }

    public boolean isAlive() { return submarine.isAlive(); }

    public Direction getDirection() { return submarine.getDirection(); }

    public ArrayList<Integer> getPosition() { return submarine.getPosition(); }

    public int getDepth() { return submarine.getDepth(); }

    public void command(String instructions) {
        instructions.chars().forEach(ch -> executeCommand(String.valueOf((char) ch)));
    }
    private void executeCommand(String instruction) {
        switch (instruction) {
            case "d":
                submarine.descendOne();
                break;
            case "u":
                submarine.ascendOne();
                break;
            case "r":
                submarine.turnRight();
                break;
            case "l":
                submarine.turnLeft();
                break;
            case "f":
                submarine.move();
                break;
            case "m":
                throwCapsule();
                break;
            default:
                break;
        }
    }

    public void throwCapsule() {
        if (submarine.getDepth() < -1)  {
            submarine = new SubmarineDead();
        }
    }
}
