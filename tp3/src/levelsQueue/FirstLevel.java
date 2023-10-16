package levelsQueue;

import java.util.ArrayList;

public class FirstLevel extends Level {
    @Override
    public void throwBomb() { }

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
