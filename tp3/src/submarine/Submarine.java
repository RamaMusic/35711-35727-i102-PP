package submarine;

import position.Direction;
import position.Point;

import java.util.HashMap;

public abstract class Submarine {

    protected Direction direction;
    protected Point position;

    public Submarine(Point position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public abstract String getPosition();
    public abstract Direction getDirection();
    public abstract int getDepth();
    public abstract boolean isDead();
    public abstract Submarine throwCapsule();
    public abstract void turnLeft();
    public abstract void turnRight();
    public abstract void moveForward();
    public abstract void ascend();
    public abstract void descend();
}

class SubmarineAlive extends Submarine {
    public SubmarineAlive(Point position, Direction direction) {
        super(position, direction);
    }

    @Override
    public String getPosition() { return position.toString(); }

    @Override
    public Direction getDirection() { return direction; }

    @Override
    public int getDepth() { return position.getZ(); }

    @Override
    public boolean isDead() { return false; }

    @Override
    public Submarine throwCapsule() {
        if (position.getZ() < -1) return new SubmarineDead(position, direction);
        return this;
    }

    @Override
    public void turnLeft() { direction = direction.turnLeft(); }
    @Override
    public void turnRight() { direction = direction.turnRight(); }
    @Override
    public void moveForward() { position = direction.move(position); }
    @Override
    public void ascend() { position = position.ascend(); }
    @Override
    public void descend() { position = position.descend(); }
}

class SubmarineDead extends Submarine {

    public SubmarineDead(Point position, Direction direction) {
        super(position, direction);
    }

    public static RuntimeException SUBMARINE_HAS_EXPLODED = new RuntimeException("El submarino ha explotado.");

    @Override
    public String getPosition() { throw SUBMARINE_HAS_EXPLODED; } // TODO Preguntar a emilio si la posición se devuelve incluso cuando explotó.

    @Override
    public Direction getDirection() { throw SUBMARINE_HAS_EXPLODED; }

    @Override
    public int getDepth() { throw SUBMARINE_HAS_EXPLODED; }

    @Override
    public Submarine throwCapsule() { throw SUBMARINE_HAS_EXPLODED; }

    @Override
    public boolean isDead() { return true; }

    @Override
    public void turnLeft() { throw SUBMARINE_HAS_EXPLODED; }
    @Override
    public void turnRight() { throw SUBMARINE_HAS_EXPLODED; }
    @Override
    public void moveForward() { throw SUBMARINE_HAS_EXPLODED; }
    @Override
    public void ascend() { throw SUBMARINE_HAS_EXPLODED; }
    @Override
    public void descend() { throw SUBMARINE_HAS_EXPLODED; }
}