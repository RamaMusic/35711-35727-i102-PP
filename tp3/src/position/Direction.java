package position;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Direction {

    public abstract Direction turnLeft();

    public abstract Direction turnRight();

    public abstract ArrayList<Integer> getVector();

    public int hashCode() { return Objects.hash(this.getClass()); }

    public boolean equals(Object other) {
        return other.getClass() == this.getClass();
    }

    public Point move(Point position) { return position.add(getVector()); }

}
