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
        System.out.println("El turno actual es: " + game.getStatus());
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
        assertEquals(new RedTurn(), game.getStatus());
    }

    @Test
    public void test05CannotPlaceChipOutsideTheBoard() {
        assertThrowsError(() -> game.playRedAt(5), "Column must be between 1 and 4.");
    }

    @Test
    public void test06PlacingAChipOutsideALargerBoardThrowsDifferentExceptionMessage() {
        game = new Linea(6, 6, 'A');
        assertThrowsError(() -> game.playRedAt(8), "Column must be between 1 and 6.");
    }

    @Test
    public void test07CannotPlaceChipAtColumnZero() {
        assertThrowsError(() -> game.playRedAt(0), "Column must be between 1 and 4.");
    }

    @Test
    public void test08BoardPrintsCorrectly() {
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
    public void test09boardPrintsCorrectlyOnBiggerBoard() {
        game = new Linea( 6,6,'A' );
        String expected = """

                ║ · · · · · · ║
                ║ · · · · · · ║
                ║ · · · · · · ║
                ║ · · · · · · ║
                ║ · · · · · · ║
                ║ · · · · · · ║
                ╚═════════════╝
                  1 2 3 4 5 6
                It's red's turn!
                """;
        assertEquals(expected, game.show());
    }

    @Test
    public void test10BoardPrintsRedChipsCorrectly() {
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
    }

    @Test
    public void test11BoardPrintsBlueChipsCorrectly() {
        playGame(3, 3);
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
    }

    @Test
    public void test12BoardPrintsCorrectlyOnRedWin() {
        playGame(1,1,2,2,3,3,4);
        String expected = """

                ║ · · · · ║
                ║ · · · · ║
                ║ O O O · ║
                ║ X X X X ║
                ╚═════════╝
                  1 2 3 4
                The game has finished.
                The winner is: Red
                """;
        assertEquals(expected, game.show());
    }

    @Test
    public void test13BoardPrintsCorrectlyOnBlueWin() {
        playGame(1,2,1,2,1,2,3,2);
        String expected = """

                ║ · O · · ║
                ║ X O · · ║
                ║ X O · · ║
                ║ X O X · ║
                ╚═════════╝
                  1 2 3 4
                The game has finished.
                The winner is: Blue
                """;
        assertEquals(expected, game.show());
    }

    @Test
    public void test14BoardPrintsCorrectlyOnDraw() {
        playDraw();
        String expected = """

                ║ O X O X ║
                ║ O X O X ║
                ║ X O X O ║
                ║ X O X O ║
                ╚═════════╝
                  1 2 3 4
                The game has ended in a Draw!
                """;
        assertEquals(expected, game.show());
    }

    @Test
    public void test15CannotPlayBlueWhenItsRedTurn() {
        assertThrows(RuntimeException.class, () -> game.playBlueAt(1));
    }

    @Test
    public void test16CanPlaceOneChipInAnEmptyBoard() {
        assertEquals(Linea.RED_SLOT, playGame(3).getCharAtPosition(3, 2));
        assertEquals(new BlueTurn(), game.getStatus());
    }

    @Test public void test17ChipsStackOnTopOfEachOther() {
        assertEquals(Linea.BLUE_SLOT, playGame(3, 3).getCharAtPosition(2, 2));
    }

    @Test public void test18CannotPlayOnAFullColumn() {
        assertThrowsError(() -> playGame(3,3,3,3,3), "Column 3 is full.");
    }

    @Test
    public void test19ChipsCanStackEvenIfSameColor() {
        assertEquals(Linea.RED_SLOT, playGame(3, 2, 3).getCharAtPosition(2, 2));
    }

    @Test
    public void test20ModeAPlayerRedWinsOnHorizontalLine() {
        assertEquals(redWinsStatus(), placeRedChipsHorizontally().getStatus());
    }

    @Test
    public void test21ModeAPlayerBlueWinsOnHorizontalLine() {
        assertEquals(blueWinsStatus(), placeBlueChipsHorizontally().getStatus());
    }

    @Test
    public void test22ModeAPlayerRedWinsOnVerticalLine() {
        assertEquals(redWinsStatus(), placeRedChipsVertically().getStatus());
    }

    @Test
    public void test23ModeAPlayerBlueWinsOnVerticalLine() {
        assertEquals(blueWinsStatus(), placeBlueChipsVertically().getStatus());
    }

    @Test
    public void test24ModeAPlayerRedDoesNotWinOnDiagonalLine() { assertFalse(placeRedChipsDiagonally().finished()); }

    @Test
    public void test25ModeAPlayerBlueDoesNotWinOnDiagonalLine() { assertFalse(placeBlueChipsDiagonally().finished()); }

    @Test
    public void test26ModeAGameCanFinishOnDrawIfNoneWins() {
        assertEquals(drawStatus(), playDraw().getStatus());
    }

    @Test
    public void test27ModeBPlayerRedDoesNotWinOnHorizontalLine() {
        game = newLineaB();
        assertFalse(placeRedChipsHorizontally().finished());
    }

    @Test
    public void test28ModeBPlayerBlueDoesNotWinOnHorizontalLine() {
        game = newLineaB();
        assertFalse(placeBlueChipsHorizontally().finished());
    }

    @Test
    public void test29ModeBPlayerRedDoesNotWinOnVerticalLine() {
        game = newLineaB();
        assertFalse(placeRedChipsVertically().finished());
    }

    @Test
    public void test30ModeBPlayerBlueDoesNotWinOnVerticalLine() {
        game = newLineaB();
        assertFalse(placeBlueChipsVertically().finished());
    }

    @Test
    public void test31ModeBPlayerRedWinsOnDiagonalLine() {
        game = newLineaB();
        assertEquals(redWinsStatus(), placeRedChipsDiagonally().getStatus());
    }

    @Test
    public void test32ModeBPlayerBlueWinsOnDiagonalLine() {
        game = newLineaB();
        assertEquals(blueWinsStatus(), placeBlueChipsDiagonally().getStatus());
    }

    @Test
    public void test33ModeBGameCanFinishOnDrawIfNoneWins() {
        game = newLineaB();
        assertEquals(drawStatus(), playDraw().getStatus());
    }

    @Test
    public void test34ModeCPlayerRedWinsOnHorizontalLine() {
        game = newLineaC();
        assertEquals(redWinsStatus(), placeRedChipsHorizontally().getStatus());
    }

    @Test
    public void test35ModeCPlayerBlueWinsOnHorizontalLine() {
        game = newLineaC();
        assertEquals(blueWinsStatus(), placeBlueChipsHorizontally().getStatus());
    }

    @Test
    public void test36ModeCPlayerRedWinsOnVerticalLine() {
        game = newLineaC();
        assertEquals(redWinsStatus(), placeRedChipsVertically().getStatus());
    }

    @Test
    public void test37ModeCPlayerBlueWinsOnVerticalLine() {
        game = newLineaC();
        assertEquals(blueWinsStatus(), placeBlueChipsVertically().getStatus());
    }

    @Test
    public void test38ModeCPlayerRedWinsOnDiagonalLine() {
        game = newLineaC();
        assertEquals(redWinsStatus(), placeRedChipsDiagonally().getStatus());
    }

    @Test
    public void test39ModeCPlayerBlueWinsOnDiagonalLine() {
        game = newLineaC();
        assertEquals(blueWinsStatus(), placeBlueChipsDiagonally().getStatus());
    }

    @Test
    public void test40ModeCGameCanFinishOnDrawIfNoneWins() {
        game = newLineaC();
        assertEquals(drawStatus(), playDraw().getStatus());
    }

    @Test
    public void test41BlueWinsDiagonallyIn6x6Board() {
        game = new Linea(6, 6, 'C');
        assertTrue(playGame(1, 2, 2, 3, 4, 3, 3, 4, 3, 4, 2, 5, 5, 5, 1, 5).finished());
    }

    private Linea placeRedChipsHorizontally() { return playGame(1, 1, 2, 2, 3, 3, 4); }

    private Linea placeBlueChipsHorizontally() { return playGame(1, 2, 3, 1, 4, 2, 1, 3, 2,4); }

    private Linea placeRedChipsVertically() { return playGame(1, 2, 1, 2, 1, 2, 1); }

    private Linea placeBlueChipsVertically() { return playGame(1, 2, 3, 2, 4, 2, 1, 2); }

    private Linea placeRedChipsDiagonally() { return playGame(1, 2, 2, 3, 4, 3, 3, 4, 3, 4, 4); }

    private Linea placeBlueChipsDiagonally() { return playGame(1, 2, 3, 4, 1, 3, 2, 2, 1, 1);}

    private Linea playDraw() { return playGame(1, 2, 3, 4, 1, 2, 3, 4, 2, 1, 4, 3, 2, 1, 4, 3); }

    private Linea playGame(int ... moves) {
        for (int i = 0; i < moves.length; i += 2) {
            game.playRedAt(moves[i]);
            if (i + 1 ==  moves.length) break;
            game.playBlueAt(moves[i + 1]);
        }
        return game;
    }

    private void assertThrowsError( Executable runnable, String expectedError ) {
        String actualError = assertThrows(RuntimeException.class, runnable, "Expected Error was not thrown.").getMessage();
        assertEquals(actualError, expectedError);
    }

    public GameManager redWinsStatus() { return new GameFinished('X'); }

    public GameManager blueWinsStatus() { return new GameFinished('O'); }

    public GameManager drawStatus() { return new Draw(); }
    private Linea newLineaB() { return new Linea(4, 4, 'B'); }

    private Linea newLineaC() { return new Linea(4, 4, 'C'); }
}
