package submarine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import position.Direction;
import position.EastDirection;
import position.NorthDirection;
import position.SouthDirection;
import position.WestDirection;
import position.Point;

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
        submarine.command("");
        checkPositionDirectionAndDepth(new Point(2, 8), south(), 0);
    }

    @Test
    public void test03CommandDDescends() {
        submarine.command("d");
        assertDepthIs(-1);
    }

    @Test
    public void test04CommandUAscends() {
        submarine.command("d");
        assertDepthIs(-1);
        submarine.command("u");
        assertDepthIs(0);
    }



    @Test
    public void test05CommandRRotatesToTheRight() {
        submarine.command("r");
        assertEquals(east(), submarine.getDirection());
    }

    @Test
    public void test06CommandLRotatesToTheLeft() {
        submarine.command("l");
        assertEquals(west(), submarine.getDirection());
    }

    @Test
    public void test07CommandFMovesForward() {
        submarine.command("f");
        assertEquals(new Point(0,1), submarine.getPosition());
    }

    @Test
    public void test08CommandMThrowsCapsule() {
        assertDepthIs(0);
        submarine.command("m");
        assertDepthIs(0);
    }

    @Test
    public void test09CommandsCanBeSentAsAString() {
        submarine.command("ddrf");
        checkPositionDirectionAndDepth(new Point(1,0), east(), -2);
    }

    @Test
    public void test10CommandsCanBeSentAsChar() {
        assertDepthIs(0);
        submarine.command('d');
        assertDepthIs(-1);
    }

    @Test
    public void test11CommandsAreNotCaseSensitive() {
        assertDepthIs(0);
        submarine.command("Dd");
        assertDepthIs(-2);
    }

    @Test
    public void test12NorthRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), north(), east(), west()));
    }

    @Test
    public void test13SouthRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), south(), west(), east()));
    }

    @Test
    public void test14EastRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), east(), south(), north()));
    }

    @Test
    public void test15WestRotatesCorrectly() {
        assertTrue(DirectionRotatesCorrectly(zeroPoint(), west(), north(), south()));
    }

    @Test
    public void test16EachDirectionHasCorrectVector() {
        submarine.command("ffrffrffrffr");
        checkPositionDirectionAndDepth(zeroPoint(), north(), 0);
    }

    // TODO Falta refactorizar todos los tests de arriba. Eso sería todo lo relacionado a dirección - rotación.

    @Test
    public void test17DepthIsLimitedTo0() {
        assertTrue(assertThrowsErrorAndMessage("You can not go up from the surface level.", () -> submarine.command("u") ) );
        assertDepthIs(0);
    }

    @Test
    public void test18ComplexMovement() { // TODO Ver por qué podemos cambiar este test, es bastante inútil.
        submarine.command("frfdddddflfuuu");
        checkPositionDirectionAndDepth(new Point(2,2), north(), -2);
    }

    @Test
    public void test19BombIsThrownInTheSurface() {
        submarine.command("m");
        checkPositionDirectionAndDepth(zeroPoint(), north(), 0);
    }

    @Test
    public void test20BombIsThrownInShallowWaters() {
        submarine.command("dm");
        checkPositionDirectionAndDepth(new Point(0,0), north(), -1);
    }

    @Test
    public void test21CanNotThrowCapsuleInDeepWaters() {
        assertTrue(assertThrowsErrorAndMessage("The submarine has exploded!", () -> submarine.command("ddm") ) );
    }

    @Test
    public void test22LastPositionAndDirectionIsWhereItHasExploded() {
        assertTrue(assertThrowsErrorAndMessage("The submarine has exploded!", () -> submarine.command("ddffmfrf") ) );
        checkPositionDirectionAndDepth(new Point(0,2), north(), -2);
        assertEquals(new Point(0,2), submarine.getPosition());
    }

    @Test
    public void test23SubmarineCanThrowMultipleCapsules() {
        submarine.command("mmmdmmdf");
        checkPositionDirectionAndDepth(new Point(0,1), north(), -2);
    }

    @Test
    public void test24SubmarineCanNotGoUpEvenIfInsisted() {
        assertTrue(assertThrowsErrorAndMessage("You can not go up from the surface level.", () -> submarine.command("uuu") ) );
        assertDepthIs(0);
        assertTrue(assertThrowsErrorAndMessage("You can not go up from the surface level.", () -> submarine.command("uuu") ) );
        assertDepthIs(0);
    }


    // TODO meter algun for extraño para mostrar que no hay limites de profundidad ni de puntos.

    private boolean assertThrowsErrorAndMessage(String error_message, Executable runnable) {
        RuntimeException exception = assertThrows(RuntimeException.class, runnable, "Expected to throw RuntimeException, but it didn't");
        return exception.getMessage().equals(error_message);
    }

    private Direction north() { return new NorthDirection(); }
    private Direction south() { return new SouthDirection(); }
    private Direction east() { return new EastDirection(); }
    private Direction west() { return new WestDirection(); }
    private Point zeroPoint() { return new Point(0,0); }

    private boolean DirectionRotatesCorrectly(Point init_poi, Direction init_dir, Direction right, Direction left) {
        Nemo submarine = new Nemo(init_poi, init_dir);

        submarine.command("r");
        boolean a = submarine.getDirection().equals(right);
        submarine.command("ll");
        boolean b = submarine.getDirection().equals(left);

        return a && b;
    }

    private void assertDepthIs(int depth) {
        assertEquals(depth, submarine.getDepth());
    }

    private void checkPositionDirectionAndDepth(Point position, Direction direction, int depth) {
        assertEquals(position, submarine.getPosition());
        assertEquals(direction, submarine.getDirection());
        assertDepthIs(depth);
    }
}
