package submarine;

import java.util.ArrayList;

public abstract class Nemo {

    public abstract void command(String instructions);
    
    public abstract boolean isAlive();
    
    public abstract Direction getDirection();
    
    public abstract ArrayList<Integer> getPosition();
    
    public abstract int getDepth();    
}

class NemoAlive extends Nemo {
    private int depth = 0;

    private Direction direction;

    private ArrayList<Integer> position = new ArrayList<Integer>();

    private boolean isAlive = true; // false == explotó

    public NemoAlive(int x, int y, Direction direction) {
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
            case "f":
                move();
                break;
            case "m":
                throwCapsule();
                break;
            default:
                break;
        }
    }

    public boolean isAlive() { return this.isAlive; }

    private void move() {
        position.set(0, position.get(0) + direction.getVector().get(0));
        position.set(1, position.get(1) + direction.getVector().get(1));
    }

    private void throwCapsule() {
        if (this.depth < -1) this.isAlive = false;
    }
}

class NemoDead extends Nemo {

    public static RuntimeException ESTA_MUERTO_EXCEPTION = new RuntimeException("El submaino está muerto.");

    public void command(String instructions) { throw ESTA_MUERTO_EXCEPTION; };

    public boolean isAlive() { throw ESTA_MUERTO_EXCEPTION; };

    public Direction getDirection() { throw ESTA_MUERTO_EXCEPTION;}

    public ArrayList<Integer> getPosition() { throw ESTA_MUERTO_EXCEPTION; }

    public int getDepth() { throw ESTA_MUERTO_EXCEPTION; }

}    