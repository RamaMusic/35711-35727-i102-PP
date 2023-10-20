package submarine;

import java.util.ArrayList;
import java.util.Objects;

public class Point {

    private int x;
    private int y;

    public Point( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Point add(ArrayList<Integer> vector) {
        return new Point( x + vector.get( 0 ), y + vector.get( 1 ) );
    }

    public boolean equals( Object other ) {
        if ( other.getClass() != this.getClass() ) return false;
        Point otherPoint = (Point) other;
        return otherPoint.getX().equals( this.getX() ) && otherPoint.getY().equals( this.getY() );
    }

    public int hashCode() { return Objects.hash( x, y ); }

    public Integer getX() { return x; }

    public Integer getY() { return y; }

}
