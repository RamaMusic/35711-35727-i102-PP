package submarineOLD.submarine;

import java.util.ArrayList;

public abstract class Submarine {
    
    public abstract boolean isAlive();
    
    public abstract Direction getDirection();
    
    public abstract ArrayList<Integer> getPosition();
    
    public abstract int getDepth();

    public abstract void turnLeft();

    public abstract void turnRight();

    public abstract void descendOne();

    public abstract void ascendOne();
    public abstract void move();
}

class SubmarineAlive extends Submarine {
    private int depth = 0;

    private Direction direction;

    private ArrayList<Integer> position = new ArrayList<Integer>();

    private boolean isAlive = true; // false == explotó

    public SubmarineAlive(int x, int y, Direction direction) {
        this.position.add(x);
        this.position.add(y);
        this.direction = direction;
    }

    public Direction getDirection() { return direction; }

    public ArrayList<Integer> getPosition() { return position; }

    public int getDepth() { return depth; }

    public boolean isAlive() { return this.isAlive; }

    public void move() {
        position.set(0, position.get(0) + direction.getVector().get(0));
        position.set(1, position.get(1) + direction.getVector().get(1));
    }

    public void turnLeft() { direction = direction.turnLeft(); }
    public void turnRight() { direction = direction.turnRight(); }
    public void descendOne() { depth--; }
    public void ascendOne() { depth++; }
}

class SubmarineDead extends Submarine {

    public static RuntimeException ESTA_MUERTO_EXCEPTION = new RuntimeException("El submaino está muerto.");

    public void command(String instructions) { throw ESTA_MUERTO_EXCEPTION; };

    public boolean isAlive() { throw ESTA_MUERTO_EXCEPTION; };

    public Direction getDirection() { throw ESTA_MUERTO_EXCEPTION;}

    public ArrayList<Integer> getPosition() { throw ESTA_MUERTO_EXCEPTION; }

    public int getDepth() { throw ESTA_MUERTO_EXCEPTION; }
    public void turnLeft() { throw ESTA_MUERTO_EXCEPTION; }
    public void turnRight() { throw ESTA_MUERTO_EXCEPTION;}
    public void descendOne() { throw ESTA_MUERTO_EXCEPTION; }
    public void ascendOne(){ throw ESTA_MUERTO_EXCEPTION; }
    public void move() { throw ESTA_MUERTO_EXCEPTION; }
}    