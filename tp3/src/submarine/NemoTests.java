package submarine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NemoTests {

    Nemo submarine;

    @BeforeEach
    public void setUp() {
        submarine = new Nemo(zeroPoint(), north());
    }

    @Test
    public void test01SubmarineStartsInCorrectPlace() {
        submarine = new Nemo(new Point(2, 4), south());
        checkPositionDirectionAndDepth(new Point(2, 4), south(), 0);
    }

    @Test
    public void test02CommandCanBeEmpty() {
        submarine = new Nemo(new Point(2, 8), south());
        checkPositionDirectionAndDepthBeforeAndAfterCommand(() -> submarine.command(""), new Point(2, 8), south(), 0);
    }

    @Test
    public void test03CommandDDescends() {
        checkDepthBeforeAndAfterCommand("d", 0, -1);
    }

    @Test
    public void test04CommandUAscends() {
        submarine.command("d");
        checkDepthBeforeAndAfterCommand("u", -1, 0);
    }

    @Test
    public void test05CommandRRotatesToTheRight() {
        submarine.command("r");
        checkPositionDirectionAndDepth(zeroPoint(), east(), 0);
    }

    @Test
    public void test06CommandLRotatesToTheLeft() {
        submarine.command("l");
        checkPositionDirectionAndDepth(zeroPoint(), west(), 0);
    }

    @Test
    public void test07CommandFMovesForward() {
        submarine.command("f");
        checkPositionDirectionAndDepth(new Point(0,1), north(), 0);
    }

    @Test
    public void test08CommandMThrowsCapsule() {
        checkDepthBeforeAndAfterCommand("m", 0, 0);
    }

    @Test
    public void test09CommandsCanBeSentAsAString() {
        submarine.command("ddrf");
        checkPositionDirectionAndDepth(new Point(1,0), east(), -2);
    }

    @Test
    public void test10CommandsCanBeSentAsChar() {
        assertEquals(0, submarine.getDepth());
        submarine.command('d');
        assertEquals(-1, submarine.getDepth());
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
        submarine.command("ffrffrffrffr");
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
        submarine.command("frfdddddflfuuu");
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
        assertThrowsErrorAndMessage(DeepWaters.SUBMARINE_HAS_EXPLODED, () -> submarine.command("ddm") );
    }

    @Test
    public void test22LastPositionAndDirectionIsWhereItHasExploded() {
        submarine.command("ddff");
        // TODO Is this okay?
        checkPositionDirectionAndDepthBeforeAndAfterCommand(
                () -> assertThrowsErrorAndMessage(DeepWaters.SUBMARINE_HAS_EXPLODED, () -> submarine.command("mfrf") )
                , new Point(0,2),
                north(),
                -2
        );
    }

    @Test
    public void test23SubmarineCanThrowMultipleCapsules() {
        submarine.command("mmmdmmdf");
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
        Nemo submarine = new Nemo(init_poi, init_dir);

        submarine.command("r");
        boolean a = submarine.getDirection().equals(right);
        submarine.command("ll");
        boolean b = submarine.getDirection().equals(left);

        return a && b;
    }

    private void checkPositionDirectionAndDepth(Point position, Direction direction, int depth) {
        assertEquals(position, submarine.getPosition());
        assertEquals(direction, submarine.getDirection());
        assertEquals(depth, submarine.getDepth());
    }

    private void checkDepthBeforeAndAfterCommand(String command, int depth, int expectedDepth) {
        assertEquals(depth, submarine.getDepth());
        submarine.command( command );
        assertEquals(expectedDepth, submarine.getDepth());
    }

    private void checkCapsuleDoesNotAffectPosition(String command, int depth, int expectedDepth) {
        checkPositionDirectionAndDepth(zeroPoint(), north(), depth);
        submarine.command( command );
        checkPositionDirectionAndDepth(zeroPoint(), north(), expectedDepth);
    }

    private void checkPositionDirectionAndDepthBeforeAndAfterCommand(Runnable runnable, Point position, Direction direction, int depth) {
        checkPositionDirectionAndDepth(position, direction, depth);
        runnable.run();
        checkPositionDirectionAndDepth(position, direction, depth);
    }
}
