package levelsQueue;

import java.util.ArrayList;

public class DeepWatersLevel extends Level {
    public static RuntimeException SUBMARINE_HAS_EXPLODED = new RuntimeException("The submarine has exploded!");

    @Override
    public void throwBomb() { throw SUBMARINE_HAS_EXPLODED; }

    @Override
    public ArrayList<Level> goUp(ArrayList<Level> data, int depth) {
        data.remove(depth);
        return data;
    }

    @Override
    public ArrayList<Level> goDown(ArrayList<Level> data, int depth) {
        data.add( new DeepWatersLevel() );
        return data;
    }
}
