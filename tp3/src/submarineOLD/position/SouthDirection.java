package submarineOLD.position;

import java.util.ArrayList;
import java.util.Arrays;

public class SouthDirection extends Direction {
    @Override
    public Direction turnLeft() {
        return new EastDirection();
    }

    @Override
    public Direction turnRight() {
        return new WestDirection();
    }

    @Override
    public String getDirectionStr() {
        return "s";
    }

    @Override
    public Point move(Point position) {
        return new Point(position.getX(), position.getY() - 1, position.getZ());
    }

    @Override
    public ArrayList<Integer> getVector() {
        return new ArrayList<>(Arrays.asList(0, -1));
    }
}
