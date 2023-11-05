package Linea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTest {

    private Linea game;

    @BeforeEach
    public void setUp() {
        game = new Linea( 4,4,'A' );
    }

    @Test
    public void test01CannotInitializeBoardSmallerThan4x4() {
        assertThrowsError(() -> new Linea(3, 4, 'A'), "Base and height must be greater than 3");
    }

    @Test
    public void test02CannotInitializeBoardOnInvalidGamemode() {
        assertThrowsError(() -> new Linea(4, 4, 'E'), "Gamemode must be A, B or C");
    }

    @Test
    public void test03BoardIsInitializedEmptyCorrectly() {
        for (int row = 1; row <= 4; row++) {
            for (int column = 1; column <= 4; column++) {
                assertEquals(game.getCharAtPosition(row, column), Linea.emptySlot);
            }
        }

        assertFalse(game.finished());
    }

    @Test
    public void test04FirstTurnIsRed() {
        assertTrue(game.isRedTurn());
    }

    @Test
    public void test05CannotPlaceChipOutsideTheBoard() {
        assertThrowsError(() -> game.playRedAt(5), "Column must be between 1 and 4");
    }

    @Test
    public void test05BPlacingAChipOutsideALargerBoardThrowsDifferentExceptionMessage() {
        game = new Linea(6, 6, 'A');
        assertThrowsError(() -> game.playRedAt(8), "Column must be between 1 and 6");
    }

    @Test
    public void test06CCannotPlaceChipAtColumnZero() {
        assertThrowsError(() -> game.playRedAt(0), "Column must be between 1 and 4");
    }

    @Test
    public void test07BoardPrintsCorrectly() {

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
    public void test08CannotPlayBlueWhenItsRedTurn() {
        assertThrows(RuntimeException.class, () -> game.playBlueAt(1));
    }

    @Test
    public void test09CanPlaceOneChipInAnEmptyBoard() {
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

    @Test public void test10ChipsStackOnTopOfEachOther() {
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

    @Test public void test11CannotPlayOnAFullColumn() {
        game.playRedAt(3).playBlueAt(3).playRedAt(3).playBlueAt(3);

        assertThrowsError(() -> game.playRedAt(3), "Column 3 is full");
    }

    @Test
    public void test12ChipsCanStackEvenIfSameColor() {
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

    private void assertThrowsError( Executable runnable, String expectedError ) {
        String actualError = assertThrows(RuntimeException.class, runnable, "Expected Error was not thrown.").getMessage();
        assertEquals(actualError, expectedError);
    }
}
