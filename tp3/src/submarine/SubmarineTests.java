package submarine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void test11NorthRotatesCorrectly() { AssertDirectionRotatesCorrectly(zeroPoint(), north(), east(), west()); }

    @Test
    public void test12SouthRotatesCorrectly() { AssertDirectionRotatesCorrectly(zeroPoint(), south(), west(), east()); }

    @Test
    public void test13EastRotatesCorrectly() { AssertDirectionRotatesCorrectly(zeroPoint(), east(), south(), north()); }

    @Test
    public void test14WestRotatesCorrectly() { AssertDirectionRotatesCorrectly(zeroPoint(), west(), north(), south()); }

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
    public void test19CapsuleIsThrownInTheSurface() {
        checkCapsuleDoesNotAffectPosition("m", 0, 0);
    }

    @Test
    public void test20CapsuleIsThrownInShallowWaters() {
        checkCapsuleDoesNotAffectPosition("dm", 0, -1);
    }

    @Test
    public void test21CanNotThrowCapsuleInDeepWaters() { assertSubmarineHasExplodedWithCommand("ddm"); }

    @Test
    public void test22LastPositionAndDirectionIsWhereItHasExploded() {
        nemo.command("ddff");
        checkPositionDirectionAndDepthBeforeAndAfterCommand( () -> assertSubmarineHasExplodedWithCommand("mfrf"), new Point(0,2),north(),-2 );
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

    private void assertSubmarineHasExplodedWithCommand(String command) {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> nemo.command(command), "Expected to throw RuntimeException, but it didn't");
        assertEquals(exception.getMessage(), DeepWaters.SUBMARINE_HAS_EXPLODED);
    }

    private void checkPositionDirectionAndDepthBeforeAndAfterCommand(Runnable runnable, Point position, Direction direction, int depth) {
        checkPositionDirectionAndDepth(position, direction, depth);
        runnable.run();
        checkPositionDirectionAndDepth(position, direction, depth);
    }

    private void AssertDirectionRotatesCorrectly(Point init_poi, Direction init_dir, Direction right, Direction left) {
        Submarine nemo = new Submarine(init_poi, init_dir);

        nemo.command("r");
        assertEquals(right, nemo.getDirection());

        nemo.command("ll");
        assertEquals(left, nemo.getDirection());
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
}
