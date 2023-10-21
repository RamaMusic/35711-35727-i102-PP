package submarine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SubmarineTests {

    Submarine nemo;

    @BeforeEach
    public void setUp() {
        nemo = new Submarine(zeroPoint(), north());
    }

    @Test
    public void test01SubmarineStartsInCorrectPlace() {
        nemo = new Submarine(new Point(2, 4), south());
        checkPositionDirectionAndDepth(new Point(2, 4), south(), 0);
    }

    @Test
    public void test02CommandCanBeEmpty() {
        nemo = new Submarine(new Point(2, 8), south());
        checkPositionDirectionAndDepthBeforeAndAfterCommand(() -> nemo.command(""), new Point(2, 8), south(), 0);
    }

    @Test
    public void test03CommandDDescends() {
        checkDepthBeforeAndAfterCommand("d", 0, -1);
    }

    @Test
    public void test04CommandUAscends() {
        nemo.command("d");
        checkDepthBeforeAndAfterCommand("u", -1, 0);
    }

    @Test
    public void test05CommandRRotatesToTheRight() {
        nemo.command("r");
        checkPositionDirectionAndDepth(zeroPoint(), east(), 0);
    }

    @Test
    public void test06CommandLRotatesToTheLeft() {
        nemo.command("l");
        checkPositionDirectionAndDepth(zeroPoint(), west(), 0);
    }

    @Test
    public void test07CommandFMovesForward() {
        nemo.command("f");
        checkPositionDirectionAndDepth(new Point(0,1), north(), 0);
    }

    @Test
    public void test08CommandMThrowsCapsule() {
        checkDepthBeforeAndAfterCommand("m", 0, 0);
    }

    @Test
    public void test09CommandsCanBeSentAsAString() {
        nemo.command("ddrf");
        checkPositionDirectionAndDepth(new Point(1,0), east(), -2);
    }

    @Test
    public void test10CommandsCanBeSentAsChar() {
        assertEquals(0, nemo.getDepth());
        nemo.command('d');
        assertEquals(-1, nemo.getDepth());
    }

    @Test
    public void test11NorthRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), north(), east(), west()));
    }

    @Test
    public void test12SouthRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), south(), west(), east()));
    }

    @Test
    public void test13EastRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), east(), south(), north()));
    }

    @Test
    public void test14WestRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), west(), north(), south()));
    }

    @Test
    public void test15EachDirectionHasCorrectVector() {
        nemo.command("ffrffrffrffr");
        checkPositionDirectionAndDepth(zeroPoint(), north(), 0);
    }

    @Test
    public void test16DepthIsLimitedTo0() {
        checkDepthBeforeAndAfterCommand("u", 0, 0);
    }

    @Test
    public void test17SubmarineCanNotGoUpEvenIfInsisted() {
        checkDepthBeforeAndAfterCommand("uuu", 0, 0);
    }

    @Test
    public void test18ComplexMovement() {
        nemo.command("frfdddddflfuuu");
        checkPositionDirectionAndDepth(new Point(2,2), north(), -2);
    }

    @Test
    public void test19BombIsThrownInTheSurface() {
        checkCapsuleDoesNotAffectPosition("m", 0, 0);
    }

    @Test
    public void test20BombIsThrownInShallowWaters() {
        checkCapsuleDoesNotAffectPosition("dm", 0, -1);
    }

    @Test
    public void test21CanNotThrowCapsuleInDeepWaters() {
        assertThrowsErrorAndMessage(DeepWaters.SUBMARINE_HAS_EXPLODED, () -> nemo.command("ddm") );
    }

    @Test
    public void test22LastPositionAndDirectionIsWhereItHasExploded() {
        nemo.command("ddff");
        checkPositionDirectionAndDepthBeforeAndAfterCommand(
                () -> assertThrowsErrorAndMessage(DeepWaters.SUBMARINE_HAS_EXPLODED, () -> nemo.command("mfrf") )
                , new Point(0,2),north(),-2);
    }

    @Test
    public void test23SubmarineCanThrowMultipleCapsules() {
        nemo.command("mmmdmmdf");
        checkPositionDirectionAndDepth(new Point(0,1), north(), -2);
    }

    private Direction north() { return new NorthDirection(); }
    private Direction south() { return new SouthDirection(); }
    private Direction east() { return new EastDirection(); }
    private Direction west() { return new WestDirection(); }
    private Point zeroPoint() { return new Point(0,0); }

    private void assertThrowsErrorAndMessage(String error_message, Executable runnable) {
        RuntimeException exception = assertThrows(RuntimeException.class, runnable, "Expected to throw RuntimeException, but it didn't");
        assertEquals(exception.getMessage(), error_message);
    }

    private boolean DirectionRotatesCorrectly(Point init_poi, Direction init_dir, Direction right, Direction left) {
        Submarine nemo = new Submarine(init_poi, init_dir);

        nemo.command("r");
        boolean a = nemo.getDirection().equals(right);
        nemo.command("ll");
        boolean b = nemo.getDirection().equals(left);

        return a && b;
    }

    private void checkPositionDirectionAndDepth(Point position, Direction direction, int depth) {
        assertEquals(position, nemo.getPosition());
        assertEquals(direction, nemo.getDirection());
        assertEquals(depth, nemo.getDepth());
    }

    private void checkDepthBeforeAndAfterCommand(String command, int depth, int expectedDepth) {
        assertEquals(depth, nemo.getDepth());
        nemo.command( command );
        assertEquals(expectedDepth, nemo.getDepth());
    }

    private void checkCapsuleDoesNotAffectPosition(String command, int depth, int expectedDepth) {
        checkPositionDirectionAndDepth(zeroPoint(), north(), depth);
        nemo.command( command );
        checkPositionDirectionAndDepth(zeroPoint(), north(), expectedDepth);
    }

    private void checkPositionDirectionAndDepthBeforeAndAfterCommand(Runnable runnable, Point position, Direction direction, int depth) {
        checkPositionDirectionAndDepth(position, direction, depth);
        runnable.run();
        checkPositionDirectionAndDepth(position, direction, depth);
    }
}
