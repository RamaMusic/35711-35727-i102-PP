package submarine;

import java.util.ArrayList;
import java.util.Arrays;


public class DepthState {

    private ArrayList<WaterLevel> data = new ArrayList<>( Arrays.asList( new SurfaceWaters() ) );

    public DepthState ascend() {
        data = getLastLevel().goUp( data, getLastIndex() );
        return this;
    }

    public DepthState descend() {
        data = getLastLevel().goDown( data );
        return this;
    }

    public int getDepth() {
        return - ( getLastIndex() );
    }

    public void throwBomb() { getLastLevel().throwBomb(); }

    private int getLastIndex() {
        return this.data.size() - 1;
    }

    private WaterLevel getLastLevel() { return this.data.get( getLastIndex() ); }

}
