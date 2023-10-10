package submarine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NemoTest {

    @Test
    public void test01StartsInCorrectInitialPositionDepthAndDirection() {
        Nemo submarine = new Nemo(2, 6);
        assertEquals(Arrays.asList(2, 6), submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(new NorthDirection(), submarine.getDirection());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test02StaysInPlaceIfNoInstruction(){
        Nemo submarine = new Nemo(0,0);
        submarine.command("");
        assertEquals(Arrays.asList(0,0), submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(new NorthDirection(), submarine.getDirection());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test03DescendsOneUnitWithCommandD(){
        Nemo submarine = new Nemo(0, 0);
        submarine.command("d");
        assertEquals(-1, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test04AscendsOneUnitWithCommandU(){
        Nemo submarine = new Nemo(0, 0);
        submarine.command("u");
        assertEquals(1, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test05AscendsTwoUnitsWithCommandUU() {
        Nemo submarine = new Nemo(0, 0);
        submarine.command("uu");
        assertEquals(2, submarine.getDepth());
        assertTrue(submarine.isAlive());
    }

    @Test
    public void test06VectorReturnsVector() {
        Nemo submarine = new Nemo(0, 0);
        assertEquals(Arrays.asList(0, 1), submarine.getDirection().getVector());
    }
}
