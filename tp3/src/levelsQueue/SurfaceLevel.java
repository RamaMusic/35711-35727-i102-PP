package levelsQueue;

import java.util.ArrayList;

public class SurfaceLevel extends Level {
    @Override
    public void throwBomb() { }

    @Override
    public ArrayList<Level> goUp(ArrayList<Level> data, int depth) {
        throw new RuntimeException("You can not go up from the surface level.");
    }

    @Override
    public ArrayList<Level> goDown(ArrayList<Level> data, int depth) {
        data.add( new FirstLevel() );
        return data;
    }
}
