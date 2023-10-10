package submarineOld;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class nemoTest {

    @Test
    public void test01StartsInCorrectInitialPositionDepthAndDirection() {
        Nemo submarine = new Nemo(2, 6);
        assertEquals(Arrays.asList(2, 6), submarine.getPosition());
        assertEquals(0, submarine.getDepth());
        assertEquals(0, submarine.getDirection());

    }

    @Test
    public void test02StaysInPlaceIfNoInstruction(){
        Nemo submarine = new Nemo(0,0);
        submarine.command("");
        assertEquals(Arrays.asList(0,0), submarine.getPosition());
    }

    @Test
    public void test03DescendsOneUnitWithCommandD(){
        Nemo submarine = new Nemo(0, 0);
        submarine.command("d");
        assertEquals(-1, submarine.getDepth());
    }

    @Test
    public void test04AscendsOneUnitWithCommandU(){
        Nemo submarine = new Nemo(0, 0);
        submarine.command("u");
        assertEquals(1, submarine.getDepth());
    }

    @Test
    public void test05Rotates90DegreesToTheRightWithCommandR() {
        Nemo submarine = new Nemo(0, 0);
        submarine.command("r");
        assertEquals(3, submarine.getDirection());
    }

    @Test
    public void test06Rotates90DegreesToTheLeftWithCommandL() {
        Nemo submarine = new Nemo(0, 0);
        submarine.command("l");
        assertEquals(1, submarine.getDirection());
    }

    @Test
    public void test07StaysInPlaceWithCommandRRRR() {
        Nemo submarine = new Nemo(0, 0);
        submarine.command("r");
        submarine.command("r");
        submarine.command("r");
        submarine.command("r");
        assertEquals(0, submarine.getDirection());
    }

    @Test
    public void test07StaysInPlaceWithCommandRRLL() {
        Nemo submarine = new Nemo(0, 0);
        submarine.command("r");
        submarine.command("r");
        submarine.command("l");
        submarine.command("l");
        assertEquals(0, submarine.getDirection());
    }

}
