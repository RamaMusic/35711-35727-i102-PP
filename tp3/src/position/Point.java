package position;

import java.util.ArrayList;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() { return x; }
    public Integer getY() { return y; }


    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) return false;
        Point otherPoint = (Point) other;
        return otherPoint.getX() == this.getX() && otherPoint.getY() == this.getY();
    }

    public Point add(ArrayList<Integer> vector) {
        return new Point(x + vector.get(0), y + vector.get(1));
    }
}
