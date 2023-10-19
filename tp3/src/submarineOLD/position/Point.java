package submarineOLD.position;

import java.util.ArrayList;

public class Point {
    private int x;
    private int y;
    private int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Integer getX() { return x; }
    public Integer getY() { return y; }
    public Integer getZ() { return z; }

    public Point ascend() { return new Point(x, y, z + 1); }
    public Point descend () { return new Point(x, y, z - 1); }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) return false;
        Point otherPoint = (Point) other;
        return otherPoint.getX() == this.getX() &&
                otherPoint.getY() == this.getY() &&
                otherPoint.getZ() == this.getZ();
    }

    public Point add(ArrayList<Integer> vector) {
        return new Point(x + vector.get(0), y + vector.get(1), z);
    }
}