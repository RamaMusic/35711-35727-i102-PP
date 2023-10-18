package depthLevels;

import java.util.ArrayList;

class SurfaceWaters extends WaterLevel {

    @Override
    public ArrayList<WaterLevel> goUp(ArrayList<WaterLevel> data, int depth ) {
        throw new RuntimeException( "You can not go up from the surface level." );
    }

    @Override
    public ArrayList<WaterLevel> goDown(ArrayList<WaterLevel> data ) {
        data.add( new ShallowWaters() );
        return data;
    }
}
