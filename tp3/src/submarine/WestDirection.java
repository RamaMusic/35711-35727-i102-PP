package submarine;

import java.util.ArrayList;

public class WestDirection extends Direction{
    @Override
    public Direction turnLeft() {
        return new SouthDirection();
    }

    @Override
    public Direction turnRight() {
        return new EastDirection();
    }

    @Override
    public String getDirection() {
        return "w";
    }

    @Override
    public ArrayList<Integer> getVector() {
        return null;
    }
}
