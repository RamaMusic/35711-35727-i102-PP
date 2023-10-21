package submarine;

import java.util.ArrayList;

abstract class WaterLevel {
    public abstract ArrayList<WaterLevel> goUp( ArrayList<WaterLevel> data, int depth );
    public abstract ArrayList<WaterLevel> goDown( ArrayList<WaterLevel> data );
    public void throwCapsule() { }
    public int hashCode() { return this.getClass().hashCode(); }
    public boolean equals( Object obj ) { return this.getClass().equals( obj.getClass() ); }
}

