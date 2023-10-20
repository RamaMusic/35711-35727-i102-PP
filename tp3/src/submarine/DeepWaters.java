package submarine;

import java.util.ArrayList;

class DeepWaters extends WaterLevel {
    public static String SUBMARINE_HAS_EXPLODED = "The submarine has exploded!";

    @Override
    public void throwBomb() { throw new RuntimeException( SUBMARINE_HAS_EXPLODED ); }

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
