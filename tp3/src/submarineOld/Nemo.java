package submarineOld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nemo {

//    private ArrayList<Integer> position = new ArrayList<>();
    private ArrayList<Integer> position = new ArrayList<>(Arrays.asList(0,0,0));

    private int direction = 0;

    public Nemo(Integer initX, Integer initY) {
        this.position.set(0, initX);
        this.position.set(1, initY);
    }

    public List<Integer> getPosition() {
        return position.subList(0, 2);
    }

    public int getDepth() {
        return this.position.get(2);
    }

    public int getDirection() {
        return this.direction;
    }


    public Nemo command(String instructions) {
        switch (instructions) {
            case "d":
                this.position.set(2, this.getDepth() - 1);
                break;
            case "u":
                this.position.set(2, this.getDepth() + 1);
                break;
            case "r":
                this.setDirection((this.getDirection() + 3) % 4);
                break;
            case "l":
                this.setDirection((this.getDirection() + 1) % 4);
                break;
        }

        return this;
    }
    private void setPosition(int x, int y) {
        this.position.set(0, x);
        this.position.set(1, y);
    }

    private void setDirection(int newDirection) {
        this.direction = newDirection;
    }

}
