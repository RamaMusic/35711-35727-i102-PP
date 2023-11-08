package Linea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {

    public static char RED_SLOT         = 'X'; //= 'R';
    public static char BLUE_SLOT        = 'O'; //= 'B';
    public static char UNMAKRED_SLOT    = '·';

    private int BASE;
    private int HEIGHT;

    private boolean FINISHED;
    private String WINNER = "";

    private GameMode GAMEMODE;
    private Turn TURN;

    private List<List<Character>> BOARD = new ArrayList<List<Character>>();

    public Linea(int base, int height, char mode) {

        if (base < 4 || height < 4) { throw new RuntimeException("Base and height must be greater than 3."); }

        this.BASE = base;
        this.HEIGHT = height;
        this.GAMEMODE = GameMode.getGamemodeFor( mode );

        // TODO Ambos hacen lo mismo, decidir cuál es más "coherente"
        this.BOARD = IntStream.range(0, this.BASE)
                    .mapToObj(i -> new ArrayList<Character>())
                    .collect(Collectors.toList());

        // IntStream.range(0, this.height).forEach(i -> this.board.add(new ArrayList<Character>()));

        this.TURN = new RedTurn();
    }

    public Linea playRedAt( int desiredColumn ) {
        // TODO Hacer funcion para verificar quien ganó o perdió, también ver si hay empate.

        this.TURN = TURN.playRedAs( desiredColumn, this);
        return this;
    }

    public Linea playBlueAt( int desiredColumn ) {
        // TODO Hacer funcion para verificar quien ganó o perdió, también ver si hay empate.

        this.TURN = TURN.playBlueAs( desiredColumn, this);
        return this;
    }

    public void stackSlotOn( int desiredColumn, char color ) {
        desiredColumn--;

        if ( desiredColumn < 0 || desiredColumn >= this.BASE ) {
            throw new RuntimeException("Column must be between 1 and " + this.BASE + "." );
        }

        if ( this.BOARD.get( desiredColumn ).size() == this.HEIGHT ) {
            throw new RuntimeException("Column " + (desiredColumn + 1)+ " is full.");
        }

        this.BOARD.get( desiredColumn ).add( color );

        this.WINNER = color == RED_SLOT ? "Red" : "Blue"; // TODO Sacar este if y ver de hacer que no se actualice siempre si no que solo cuando ganen.
        this.FINISHED = GAMEMODE.checkVictory( color, this );
    }

    public int getHeight() { return this.HEIGHT; }

    public int getBase() { return this.BASE; }

    public List<List<Character>> getBoard() { return this.BOARD; }

    public Turn getTurn() { return this.TURN; }
// TODO buscar alternativas a esta garcha, es muy feo.
    public char getCharAtPosition(int row, int column) {
        return IntStream.range(0, this.BOARD.size())
                .filter(i -> i == column)
                .mapToObj(i -> this.BOARD.get(i))
                .findFirst()
                .orElse(Collections.emptyList())
                .stream()
                .skip(Math.max(0, this.HEIGHT - row - 1))
                .findFirst()
                .orElse(UNMAKRED_SLOT);
    }


//    public char getCharAtPosition(int row, int column) {
//        return this.BOARD.stream()
//                .skip(column)
//                .findFirst()
//                .map(list -> list.size() > this.HEIGHT - 1 - row ? list.get(this.HEIGHT - 1 - row) : UNMAKRED_SLOT)
//                .orElse(UNMAKRED_SLOT);
//    }


//    public char getCharAtPosition(int row, int column) {
//        try {
//            return this.BOARD.get(column).get(this.HEIGHT - 1 - row);
//        } catch (IndexOutOfBoundsException e) {
//            return UNMAKRED_SLOT;
//        }
//    }

    public boolean finished() {
        return FINISHED;
    }

    public String show() {
        String bottom_border = "╚" + "═".repeat(this.BASE * 2 + 1) + "╝";

        String bottom_status = TURN.getStatus() + "\n" ;

        String numbers = "  " + IntStream.rangeClosed(1, this.BASE)
                .mapToObj(i -> String.valueOf(i % 10))
                .collect(Collectors.joining(" "))
                + "\n";

        String board_content = IntStream.range(0, this.HEIGHT)
                .mapToObj(i -> "║ " + IntStream.range(0, this.BASE)
                        .mapToObj(j -> String.valueOf(this.getCharAtPosition(i, j)))
                        .collect(Collectors.joining(" "))
                        + " ║\n")
                .collect(Collectors.joining());

        if (this.finished()) {
            bottom_status = "Game finished!\n" + this.WINNER + " won!\n";
        }
        return "\n" + board_content + bottom_border + "\n" + numbers + bottom_status;
//        return "\n" + board_content + bottom_border + "\n" + numbers;
    }
}