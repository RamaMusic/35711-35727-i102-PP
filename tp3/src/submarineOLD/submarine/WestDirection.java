package submarineOLD.submarine;

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
    public ArrayList<Integer> getVector() {
        return new ArrayList<>(Arrays.asList(-1, 0));
    }
}
