package Linea;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {

    public static char RED_SLOT         = 'X'; //= 'R';
    public static char BLUE_SLOT        = 'O'; //= 'B';
    public static char UNMARKED_SLOT = '·';

    private int base;
    private int height;

    private boolean finished;

    private GameMode gameMode;
    private GameManager gameState;

    private List<List<Character>> board;

    public Linea(int base, int height, char mode) {

        if (base < 4 || height < 4) { throw new RuntimeException("Base and height must be greater than 3."); }

        this.base = base;
        this.height = height;
        this.gameMode = GameMode.getGamemodeFor( mode );

        this.board = IntStream.range(0, this.base)
                    .mapToObj(i -> new ArrayList<Character>())
                    .collect(Collectors.toList());

        this.gameState = new RedTurn();
    }

    public void playRedAt( int desiredColumn ) {
        this.gameState = gameState.playRedAs( desiredColumn, this);
        verifyIfGameHasEnded( RED_SLOT );
    }

    public void playBlueAt( int desiredColumn ) {
        this.gameState = gameState.playBlueAs( desiredColumn, this);
        verifyIfGameHasEnded( BLUE_SLOT );
    }

    public void stackSlotOn( int desiredColumn, char color ) {
        desiredColumn--;

        if ( !isColumnInBounds( desiredColumn )) { throw new RuntimeException("Column must be between 1 and " + this.base + "." ); }
        if ( this.board.get( desiredColumn ).size() == this.height) { throw new RuntimeException("Column " + (desiredColumn + 1)+ " is full."); }

        this.board.get( desiredColumn ).add( color );

        this.finished = gameMode.checkVictory( color, this );
    }

    public char getCharAtPosition(int row, int column) {
        int desiredRow = this.height - 1 - row;
        if (isColumnInBounds( column ) && desiredRow < this.board.get(column).size() && desiredRow >= 0) {
            return this.board.get(column).get(desiredRow);
        }
        return UNMARKED_SLOT;
    }

    public boolean finished() { return this.getStatus().isFinished(); }

    public String show() {
        String bottom_border = "╚" + "═".repeat(this.base * 2 + 1) + "╝";

        String bottom_status = gameState.getStatus() + "\n" ;

        String numbers = "  " + IntStream.rangeClosed(1, this.base)
                .mapToObj(i -> String.valueOf(i % 10))
                .collect(Collectors.joining(" "))
                + "\n";

        String board_content = IntStream.range(0, this.height)
                .mapToObj(i -> "║ " + IntStream.range(0, this.base)
                        .mapToObj(j -> String.valueOf(this.getCharAtPosition(i, j)))
                        .collect(Collectors.joining(" "))
                        + " ║\n")
                .collect(Collectors.joining());

        return "\n" + board_content + bottom_border + "\n" + numbers + bottom_status;
    }

    public boolean checkVerticalVictory(char slotSymbol) {
        return IntStream.range(0, this.getBase())
                .anyMatch(column -> IntStream.range(0, this.getHeight() - 3)
                        .anyMatch(row -> IntStream.range(0, 4)
                                .allMatch(i -> this.getCharAtPosition(row + i, column) == slotSymbol)));
    }

    public boolean checkHorizontalVictory(char slotSymbol) {
        return IntStream.range(0, this.getHeight())
                .anyMatch(row -> IntStream.range(0, this.getBase() - 3)
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> this.getCharAtPosition(row, column + i) == slotSymbol)));
    }

    public boolean checkDiagonalVictory(char slotSymbol) {
        return IntStream.range(0, this.getHeight() - 3)
                .anyMatch(row -> IntStream.range(0, this.getBase() - 3)
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> this.getCharAtPosition(row + i, column + i) == slotSymbol)))
                || IntStream.range(0, this.getHeight() - 3)
                .anyMatch(row -> IntStream.range(3, this.getBase())
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> this.getCharAtPosition(row + i, column - i) == slotSymbol)));
    }

    private void verifyIfGameHasEnded(char slot) { if ( this.finished) { this.gameState = new GameFinished( slot ); } }

    private boolean isColumnInBounds(int desiredColumn) { return desiredColumn >= 0 && desiredColumn < this.base; }

    public int getHeight() { return this.height; }

    public int getBase() { return this.base; }

    public List<List<Character>> getBoard() { return this.board; }

    public GameManager getStatus() { return this.gameState; }
}