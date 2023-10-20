package submarine;

import java.util.ArrayList;

class SurfaceWaters extends WaterLevel {

    @Override
    public ArrayList<WaterLevel> goUp(ArrayList<WaterLevel> data, int depth ) {
        return data;
    }

    @Override
    public ArrayList<WaterLevel> goDown(ArrayList<WaterLevel> data ) {
        data.add( new ShallowWaters() );
        return data;
    }
}
