package Linea;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LineaTest {

    private Linea game;

    @BeforeEach
    public void setUp() {
        game = new Linea( 4,4,'A' );
    }

    @AfterEach
    public void printGame() {
        System.out.println(game.show());
        System.out.println("El turno actual es: " + game.getTurn());
        System.out.println("El juego terminó: " + game.finished());
    }

    @Test
    public void test01CannotInitializeBoardSmallerThan4x4() {
        assertThrowsError(() -> new Linea(3, 4, 'A'), "Base and height must be greater than 3.");
    }

    @Test
    public void test02CannotInitializeBoardOnInvalidGamemode() {
        assertThrowsError(() -> new Linea(4, 4, 'E'), "Gamemode must be A, B or C.");
    }

    @Test
    public void test03BoardIsInitializedEmptyCorrectly() {
        IntStream.rangeClosed(1, 4)
                .forEach(row ->
                        IntStream.rangeClosed(1, 4)
                                .forEach(column ->
                                        assertEquals(game.getCharAtPosition(row, column), Linea.UNMARKED_SLOT)
                                )
                );


        assertFalse(game.finished());
    }

    @Test
    public void test04FirstTurnIsRed() {
        assertEquals(new RedTurn(), game.getTurn());
    }

    @Test
    public void test05CannotPlaceChipOutsideTheBoard() {
        assertThrowsError(() -> game.playRedAt(5), "Column must be between 1 and 4.");
    }

    @Test
    public void test05BPlacingAChipOutsideALargerBoardThrowsDifferentExceptionMessage() {
        game = new Linea(6, 6, 'A');
        assertThrowsError(() -> game.playRedAt(8), "Column must be between 1 and 6.");
    }

    @Test
    public void test06CCannotPlaceChipAtColumnZero() {
        assertThrowsError(() -> game.playRedAt(0), "Column must be between 1 and 4.");
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
                It's red's turn!
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
                It's blue's turn!
                """;
        assertEquals(expected, game.show());
        assertEquals(Linea.RED_SLOT, game.getCharAtPosition(3, 2));
        assertEquals(new BlueTurn(), game.getTurn());
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
                It's red's turn!
                """;

        assertEquals(expected, game.show());
        assertEquals(Linea.BLUE_SLOT, game.getCharAtPosition(2, 2));
    }

    @Test public void test11CannotPlayOnAFullColumn() {
        game.playRedAt(3).playBlueAt(3).playRedAt(3).playBlueAt(3);

        assertThrowsError(() -> game.playRedAt(3), "Column 3 is full.");
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
                It's blue's turn!
                """;

        assertEquals(expected, game.show());
        assertEquals(new BlueTurn(), game.getTurn());
        assertEquals(Linea.RED_SLOT, game.getCharAtPosition(2, 2));
    }

    @Test
    public void test13PlayerWinsOnTrivialHorizontalLine() {
        assertTrue(placeRedChipsHorizontally().finished());
    }

    @Test
    public void test14PlayerWinsOnTrivialVerticalLine() {
        assertTrue(placeRedChipsVertically().finished());
    }

    @Test
    public void test15PlayerDoesNotWinOnTrivialDiagonalLineAndGameModeA() {
        assertFalse(placeRedChipsDiagonally().finished());
    }

    @Test
    public void test16PlayerWinsOnTrivialDiagonalLineInGameModeB() {
        game = new Linea( 4,4,'B' );
        assertTrue(placeRedChipsDiagonally().finished());
    }

    @Test
    public void test17PlayerDoesNotWinOnTrivalVerticalLineInGameModeB() {
        game = new Linea( 4,4,'B' );
        assertFalse(placeRedChipsVertically().finished());
    }

    @Test
    public void test18PlayerDoesNotWinsOnTrivialHorizontalLineInGameModeB() {
        game = new Linea( 4,4,'B' );
        assertFalse(placeRedChipsHorizontally().finished());
    }

    @Test
    public void test19CheckDraw() {
        game = new Linea(4, 4, 'A');
        game.playRedAt(1)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(2)
                .playBlueAt(1)
                .playRedAt(4)
                .playBlueAt(3)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(2)
                .playBlueAt(1)
                .playRedAt(4)
                .playBlueAt(3);

        assertEquals(new Draw(), game.getTurn());

    }

    @Test
    public void test20BlueWinsDiagonallyIn6x6Board() {
        game = new Linea(6, 6, 'C');
        game.playRedAt(1)
                .playBlueAt(2)
                .playRedAt(2)
                .playBlueAt(3)
                .playRedAt(4)
                .playBlueAt(3)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(2)
                .playBlueAt(5)
                .playRedAt(5)
                .playBlueAt(5)
                .playRedAt(1)
                .playBlueAt(5);
        assertTrue(game.finished());
    }

    @Test
    public void test99ztesttesttest(){
        game = new Linea(20, 20, 'C');
    }

    // TODO hacer tests para ganar con azul!11uno, remover tests innecesarios, refactorizar!!1

    private Linea placeRedChipsHorizontally() {
        return game.playRedAt(1)
                .playBlueAt(1)
                .playRedAt(2)
                .playBlueAt(2)
                .playRedAt(3)
                .playBlueAt(3)
                .playRedAt(4);
    }

    private Linea placeRedChipsVertically() {
        return game.playRedAt(1)
                .playBlueAt(2)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(1)
                .playBlueAt(2)
                .playRedAt(1);
    }

    private Linea placeRedChipsDiagonally() {
        return game.playRedAt(1)
                .playBlueAt(2)
                .playRedAt(2)
                .playBlueAt(3)
                .playRedAt(4)
                .playBlueAt(3)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(3)
                .playBlueAt(4)
                .playRedAt(4);
    }

    private void assertThrowsError( Executable runnable, String expectedError ) {
        String actualError = assertThrows(RuntimeException.class, runnable, "Expected Error was not thrown.").getMessage();
        assertEquals(actualError, expectedError);
    }
}
