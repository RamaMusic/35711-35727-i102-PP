package position;

import java.util.ArrayList;
import java.util.Arrays;

public class NorthDirection extends Direction {
    @Override
    public Direction turnLeft() {
        return new WestDirection();
    }

    @Override
    public Direction turnRight() {
        return new EastDirection();
    }

    @Override
    public String getDirection() { return "n"; }

    @Override
    public Point move(Point position) {
        return new Point(position.getX(), position.getY() + 1, position.getZ());
    }

    @Override
    public ArrayList<Integer> getVector() {
        return new ArrayList<>(Arrays.asList(0, 1));
    }
}
