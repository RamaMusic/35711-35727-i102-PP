package levelsQueue;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Level {
    public abstract void throwBomb();
    public abstract ArrayList<Level> goUp(ArrayList<Level> data, int depth);
    public abstract ArrayList<Level> goDown(ArrayList<Level> data, int depth);
}

