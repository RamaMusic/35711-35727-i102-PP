package submarine;

import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<Integer> getVector() { return new ArrayList<>( Arrays.asList( 1, 0 ) ); }
}
