package submarine;

public class bombBay {

    private int bombCount;

    public bombBay() {
        this.bombCount = 0;
    }

    public boolean hasBombs() {
        return this.bombCount > 0;
    }

    public void fireBomb(Integer depth) {
        if (this.bombCount == 0) {
            throw new RuntimeException("No bombs left");
        }
        if (depth < -1) {
            throw new RuntimeException("Cannot fire bomb at depth " + depth);
        }
        this.bombCount--;
    }

    public String toString() {
        return "Bomb Bay: " + this.bombCount;
    }
}
