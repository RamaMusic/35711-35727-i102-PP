package submarine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NemoTest {

    @Test
    public void test01StartsInCorrectInitialPositionDepthAndDirection() {
        Nemo submarine = new NemoAlive(2, 6, new SouthDirection());
        assertEquals(Arrays.asList(2, 6), submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(new SouthDirection(), submarine.getDirection());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test02StaysInPlaceIfNoInstruction(){
        Nemo submarine = new NemoAlive(0,0, north());
        submarine.command("");
        assertEquals(Arrays.asList(0,0), submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(new NorthDirection(), submarine.getDirection());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test03DescendsOneUnitWithCommandD(){
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("d");
        assertEquals(-1, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test04AscendsOneUnitWithCommandU(){
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("u");
        assertEquals(1, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test05AscendsTwoUnitsWithCommandUU() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("uu");
        assertEquals(2, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test06RotatesToTheRightWithCommandR() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("r");
        assertEquals(new EastDirection(), submarine.getDirection());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test07RotatesToTheLeftWithCommandL() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("l");
        assertEquals(new WestDirection(), submarine.getDirection());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test08DoesNotChangeDirectionWithCommandRRRR() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("rrrr");
        assertEquals(north(), submarine.getDirection());
    }

    @Test public void test08MovesForwardWithCommandF() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("f");
        assertEquals(Arrays.asList(0, 1), submarine.getPosition());
        assertTrue(submarine.isAlive());
    }

    @Test public void test09MovesForwardAndLeftWithCommandFLF() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("flf");
        assertEquals(Arrays.asList(-1, 1), submarine.getPosition());
        assertTrue(submarine.isAlive());
    }

    @Test public void test10MovesForwardRightAndDownWithCommandFRFD() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("frfd");
        assertEquals(Arrays.asList(1, 1), submarine.getPosition());
        assertEquals(-1, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }

    @Test public void test11MovesCorrectlyWithAComplexCommand() {
        Nemo submarine = new NemoAlive(0, 0, north());
        submarine.command("frfdddddflfuuu");
        assertEquals(Arrays.asList(2, 2), submarine.getPosition());
        assertEquals(-2, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }
    private Direction north() { return new NorthDirection(); }
}
