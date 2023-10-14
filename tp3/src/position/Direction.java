package position;

import java.util.ArrayList;

public abstract class Direction {

    public abstract Direction turnLeft();

    public abstract Direction turnRight();

    public abstract String getDirection();

    public abstract Point move(Point position);

    public abstract ArrayList<Integer> getVector();

    public boolean equals(Object other) {
        return other.getClass() == this.getClass();
    }
}
