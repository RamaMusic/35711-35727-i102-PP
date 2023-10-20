package submarine;

import java.util.ArrayList;

class ShallowWaters extends WaterLevel {

    @Override
    public ArrayList<WaterLevel> goUp( ArrayList<WaterLevel> data, int depth ) {
        data.remove( depth );
        return data;
    }

    @Override
    public ArrayList<WaterLevel> goDown( ArrayList<WaterLevel> data ) {
        data.add( new DeepWaters() );
        return data;
    }
}
