package position;

import java.util.ArrayList;
import java.util.Arrays;

public class WestDirection extends Direction{
    @Override
    public Direction turnLeft() {
        return new SouthDirection();
    }

    @Override
    public Direction turnRight() {
        return new NorthDirection();
    }

    @Override
    public String getDirection() {
        return "w";
    }

    @Override
    public Point move(Point position) {
        return new Point(position.getX() - 1, position.getY(), position.getZ());
    }

    @Override
    public ArrayList<Integer> getVector() {
        return new ArrayList<>(Arrays.asList(-1, 0));
    }
}
