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
        assertThrows(IllegalArgumentException.class, () -> new Linea(4, 4, 'D'));
    }

    @Test
    public void test03BoardIsInitializedEmptyCorrectly() {
        Linea game = new Linea(4, 4, 'C');

        for (int row = 1; row <= 4; row++) {
            for (int column = 1; column <= 4; column++) {
                assertEquals(game.getCharAtPosition(row, column), Linea.emptySlot);
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
    public void test06BoardPrintsCorrectly() {
        Linea game = new Linea(4,4,'C');

        String expected = """
                                
                ║ · · · · ║
                ║ · · · · ║
                ║ · · · · ║
                ║ · · · · ║
                ╚═════════╝
                  1 2 3 4
                """;

        assertEquals(expected, game.show());
    }

    @Test
    public void test07CannotPlayBlueWhenItsRedTurn() {
        Linea game = new Linea(4,4, 'C');
        assertThrows(IllegalArgumentException.class, () -> game.playBlueAt(1));
    }

    @Test
    public void test08CanPlaceOneChipInAnEmptyBoard() {
        Linea game = new Linea(4,4, 'C');

        game.playRedAt(3);
        String expected = """
                                
                ║ · · · · ║
                ║ · · · · ║
                ║ · · · · ║
                ║ · · X · ║
                ╚═════════╝
                  1 2 3 4
                """;
        assertEquals(expected, game.show());
        assertEquals(Linea.redSlot, game.getCharAtPosition(4, 3));
        assertFalse(game.isRedTurn());
    }

    @Test public void test09ChipsStackOnTopOfEachOther() {
        Linea game = new Linea(4,4, 'C');

        game.playRedAt(3).playBlueAt(3);

        String expected = """
                                
                ║ · · · · ║
                ║ · · · · ║
                ║ · · O · ║
                ║ · · X · ║
                ╚═════════╝
                  1 2 3 4
                """;

        assertEquals(expected, game.show());
        assertEquals(Linea.blueSlot, game.getCharAtPosition(3, 3));
    }

    @Test public void test10CannotPlayOnAFullColumn() {
        Linea game = new Linea(4,4, 'C');

        game.playRedAt(3).playBlueAt(3).playRedAt(3).playBlueAt(3);

        assertThrows(IllegalArgumentException.class, () -> game.playRedAt(3));
    }

    @Test
    public void test11ChipsCanStackEvenIfSameColor() {
        Linea game = new Linea(4,4, 'C');

        game.playRedAt(3).playBlueAt(2).playRedAt(3);

        String expected = """
                                
                ║ · · · · ║
                ║ · · · · ║
                ║ · · X · ║
                ║ · O X · ║
                ╚═════════╝
                  1 2 3 4
                """;

        assertEquals(expected, game.show());
        assertEquals(Linea.redSlot, game.getCharAtPosition(3, 3));
    }
}
