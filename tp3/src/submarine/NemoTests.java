package submarine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import position.Direction;
import position.EastDirection;
import position.NorthDirection;
import position.SouthDirection;
import position.WestDirection;
import position.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NemoTests {

    @Test
    public void test01SubmarineStartsInCorrectPlace() {
        Nemo submarine = new Nemo(new Point(2, 4), south());
        assertEquals(new Point(2, 4), submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(south(), submarine.getDirection());
    }

    @Test
    public void test02CommandCanBeEmpty() {
        Nemo submarine = new Nemo(new Point(2, 8), south());
        submarine.command("");
        assertEquals(new Point(2, 8), submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(south(), submarine.getDirection());
    }

    @Test
    public void test03CommandDDescends() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("d");
        assertEquals(-1, submarine.getDepth());
    }

    @Test
    public void test04CommandUAscends() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("d");
        submarine.command("u");
        assertEquals(0, submarine.getDepth());
    }

    @Test
    public void test05CommandRRotatesToTheRight() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("r");
        assertEquals(east(), submarine.getDirection());
    }

    @Test
    public void test06CommandLRotatesToTheLeft() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("l");
        assertEquals(west(), submarine.getDirection());
    }

    @Test
    public void test07CommandFMovesForward() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("f");
        assertEquals(new Point(0,1), submarine.getPosition());
    }

    @Test
    public void test08CommandMThrowsCapsule() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("m");
        assertTrue(submarine.hasThrownBomb());
    }

    @Test
    public void test09CommandsCanBeSentAsAString() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("ddrf");
        assertEquals(new Point(1,0), submarine.getPosition());
        assertEquals(-2, submarine.getDepth());
        assertEquals(east(), submarine.getDirection());
    }

    @Test
    public void test10CommandsCanBeSentAsChar() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        assertEquals(0, submarine.getDepth());
        submarine.command('d');
        assertEquals(-1, submarine.getDepth());
    }

    @Test
    public void test11CommandsAreNotCaseSensitive() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        assertEquals(0, submarine.getDepth());
        submarine.command("Dd");
        assertEquals(-2, submarine.getDepth());
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
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("ffrffrffrffr");
        assertEquals(new Point(0,0), submarine.getPosition());
        assertEquals(new NorthDirection(), submarine.getDirection());
    }

    // TODO Falta refactorizar todos los tests de arriba. Eso sería todo lo relacionado a dirección - rotación.

    @Test
    public void test17DepthIsLimitedTo0() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        assertTrue(assertThrowsErrorAndMessage("You can not go up from the surface level.", () -> submarine.command("u") ) );
    }

    @Test
    public void test18ComplexMovement() { // TODO Cambiar el nombre.
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("frfdddddflfuuu");
        assertEquals(new Point(2,2), submarine.getPosition());
        assertEquals(-2, submarine.getDepth());
    }

    @Test
    public void test19BombIsThrownInTheSurface() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("m");
        assertTrue(submarine.hasThrownBomb());
    }

    @Test
    public void test20BombIsThrownInShallowWaters() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("ddm");
        assertTrue(submarine.hasThrownBomb());
    }

    @Test
    public void test21CanNotThrowCapsuleInDeepWaters() {
        Nemo submarine = new Nemo(new Point(0, 0), north());
        assertTrue(assertThrowsErrorAndMessage("The submarine has exploded!", () -> submarine.command("ddm") ) );
        assertFalse(submarine.hasThrownBomb());
    }

    @Test
    public void test22SubmarineDiesAfterCommandDFLLFFDDAAAFFDDM() { // TODO Cambiar este test a algo decente.
        Nemo submarine = new Nemo(zeroPoint(), north());
        assertTrue(assertThrowsErrorAndMessage("The submarine has exploded!", () -> submarine.command("dfllffdduuuffddm") ) );
    }


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
}
