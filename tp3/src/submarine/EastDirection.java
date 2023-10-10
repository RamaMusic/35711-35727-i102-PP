package submarine;

import java.util.ArrayList;

public class EastDirection extends Direction{
    @Override
    public Direction turnLeft() {
        return new NorthDirection();
    }

    @Override
    public Direction turnRight() {
        return new SouthDirection();
    }

    @Override
    public String getDirection() {
        return "e";
    }

    @Override
    public ArrayList<Integer> getVector() {
        return null;
    }
}
