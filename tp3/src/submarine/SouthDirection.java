package submarine;

import java.util.ArrayList;

public class SouthDirection extends Direction{
    @Override
    public Direction turnLeft() {
        return new EastDirection();
    }

    @Override
    public Direction turnRight() {
        return new WestDirection();
    }

    @Override
    public String getDirection() {
        return "s";
    }

    @Override
    public ArrayList<Integer> getVector() {
        return null;
    }
}
