package submarine;

public class Bomb {
    public void fireBomb(Integer depth) {
        if (depth < -1) {
            throw new RuntimeException("Cannot fire bomb at depth " + depth);
        }
    }
}
