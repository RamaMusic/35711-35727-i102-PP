package submarine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import position.*;

import static org.junit.jupiter.api.Assertions.*;


public class NemoTests {

    @Test
    public void test01StartsInCorrectInitialPositionDepthAndDirection() {
        Nemo submarine = new Nemo(new Point(2,6), new SouthDirection());
        assertEquals("(2,6)", submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(new SouthDirection(), submarine.getDirection());
    }

    @Test
    public void test02StaysInPlaceIfNoInstruction(){
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("");
        assertEquals("(0,0)", submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(new NorthDirection(), submarine.getDirection());
    }

    @Test
    public void test03DescendsOneUnitWithCommandD(){
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("d");
        assertEquals(-1, submarine.getDepth());
    }

    @Test
    public void test03bDescendsTwoUnitsWithCommandDD(){
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("dd");
        assertEquals(-2, submarine.getDepth());
    }

    @Test
    public void test04AscendsOneUnitWithCommandU(){
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("ddu");
        assertEquals(-1, submarine.getDepth());
    }

    @Test
    public void test05CanNotAscendAboveTheSurface() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        assertTrue(assertThrowsErrorAndMessage("You can not go up from the surface level.", () -> submarine.command("u") ) );
    }

    @Test
    public void test06RotatesToTheRightWithCommandR() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("r");
        assertEquals(new EastDirection(), submarine.getDirection());
    }

    @Test
    public void test07RotatesToTheLeftWithCommandL() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("l");
        assertEquals(new WestDirection(), submarine.getDirection());
    }

    @Test
    public void test08DoesNotChangeDirectionWithCommandRRRR() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("rrrr");
        assertEquals(north(), submarine.getDirection());
    }

    @Test
    public void test09MovesForwardWithCommandF() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("f");
        assertEquals("(0,1)", submarine.getPosition());
    }

    @Test
    public void test10MovesForwardAndLeftWithCommandFLF() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("flf");
        assertEquals("(-1,1)", submarine.getPosition());
    }

    @Test
    public void test11MovesForwardRightAndDownWithCommandFRFD() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("frfd");
        assertEquals("(1,1)", submarine.getPosition());
        assertEquals(-1, submarine.getDepth());
    }

    @Test
    public void test12MovesCorrectlyWithAComplexCommand() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        submarine.command("frfdddddflfuuu");
        assertEquals("(2,2)", submarine.getPosition());
        assertEquals(-2, submarine.getDepth());
    }

    @Test void test13SubmarineDiesWhenThrowingCapsuleBelowDepthMinusOne() {
        Nemo submarine = new Nemo(new Point(0, 0), north());
        assertTrue(assertThrowsErrorAndMessage("The submarine has exploded!", () -> submarine.command("ddm") ) );
    }

    @Test void test14SubmarineDiesAfterCommandDFLLFFDDAAAFFDDM() {
        Nemo submarine = new Nemo(zeroPoint(), north());
        assertTrue(assertThrowsErrorAndMessage("The submarine has exploded!", () -> submarine.command("dfllffdduuuffddm") ) );
    }

//    @Test void test15SubmarineThrowsExceptionAfterRunningACommandWhenSubmarineIsDead() {
//        Nemo submarine = new Nemo(new Point(0, 0), north());
//        submarine.command("ddm");
////        assertThrows(RuntimeException.class, () -> submarine.command("f"));
//    }


    private boolean assertThrowsErrorAndMessage(String desired_error_message, Executable runnable) {
        RuntimeException exception = assertThrows(RuntimeException.class, runnable, "Expected to throw RuntimeException, but it didn't");
        return exception.getMessage().equals(desired_error_message);
    }

    private Direction north() { return new NorthDirection(); }
    private Point zeroPoint() { return new Point(0,0); }
}
