package Linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTest {
    @Test
    public void test01CannotInitializeBoardSmallerThan4x4() {
        assertThrows(IllegalArgumentException.class, () -> new Linea(3, 3, 'C'));
    }

    @Test
    public void test02CannotInitializeBoardOnInvalidGamemode() {
        assertThrows(IllegalArgumentException.class, () -> new Linea(3, 3, 'D'));
    }

    @Test
    public void test03BoardIsInitializedEmptyCorrectly() {
        Linea game = new Linea(4, 4, 'C');

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(game.getPosition(i, j), '-');
            }
        }

        assertFalse(game.finished());
    }

    @Test
    public void test04FirstTurnIsRed() {
        Linea game = new Linea(4, 4, 'C');
        assertTrue(game.isRedTurn());
    }

    @Test
    public void test05CannotPlaceChipOutsideTheBoard() {
        Linea game = new Linea(4,4,'C');

        assertThrows(IllegalArgumentException.class, () -> game.playRedAt(5));
    }

    @Test
    public void test05CanPlaceOneChipInAnEmptyBoard() {
        Linea game = new Linea(4,4, 'C');

        game.playRedAt(3);
        System.out.println(game.show());
        assertEquals(Linea.RED_CHAR, game.getPosition(3-1, 4-1));
    }
}
