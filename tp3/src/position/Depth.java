package position;

import levelsQueue.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Depth {
    private ArrayList<Level> data = new ArrayList<>(Arrays.asList(new SurfaceLevel()));

    public Depth ascend() {
        data = data.get(getLastIndex()).goUp(data, getLastIndex());
        return this;
    }

    public Depth descend() {
        data = data.get(getLastIndex()).goDown(data, getLastIndex());
        return this;
    }

    private int getLastIndex() {
        return this.data.size() - 1;
    }

    public int getDepth() {
        return - (getLastIndex());
    }

    public void throwBomb() {
        data.get(getLastIndex()).throwBomb();
    }
}
