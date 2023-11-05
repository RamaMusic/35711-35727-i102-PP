package Linea;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {

    private int base;
    private int height;

    static char emptySlot = '·';
    static char redSlot = 'X'; // Rojas == Negras == X
    static char blueSlot = 'O'; // Azules == Blancas == O

    private List<List<Character>> board;

    private boolean redTurn = true;

    private boolean finished = false;


    public Linea(int base, int height, char gamemode) {

        if (base < 4 || height < 4) {
            throw new IllegalArgumentException("Base and height must be greater than 3");
        }

        //TODO cuando hagamos la clase gamemode esto cambia a un contains.
        if (gamemode != 'C' && gamemode != 'A' && gamemode != 'B') {
            throw new IllegalArgumentException("Gamemode must be A, B or C");
        }

        this.base = base;
        this.height = height;

        this.board = IntStream.range(0, this.height)
                .mapToObj(i -> IntStream.range(0, this.base)
                        .mapToObj(j -> emptySlot)
                        .collect(Collectors.toList()))
                .toList();
    }

    public char getCharAtPosition(int row, int column) {
        return board.get(row - 1).get(column - 1);
    }

    public boolean finished() { return finished; }

    public boolean isRedTurn() {
        return redTurn;
    }

    public boolean isBlueTurn() {
        return !redTurn;
    }

    public Linea playRedAt(int column) {
        if ( !this.isRedTurn() ) {
            throw new IllegalArgumentException("It's not red's turn");
        }

        playGame(column, redSlot);
        return this;
    }

    public Linea playBlueAt(int column) {
        if ( !this.isBlueTurn() ) {
            throw new IllegalArgumentException("It's not blue's turn");
        }

        playGame(column, blueSlot);
        return this;
    }

    private void playGame(int column, char slot) {
        if (column > this.base) {
            throw new IllegalArgumentException("Column must be between 1 and " + this.base);
        }

        if (this.board.get(0).get(column - 1) != emptySlot) {
            throw new IllegalArgumentException("Column " + column + " is full");
        }

        int row = IntStream.range(0, this.height)
                .filter(r -> this.board.get(r).get(column - 1) == emptySlot)
                .reduce((first, second) -> second)
                .orElse(this.height - 1);

        this.board.get(row).set(column - 1, slot);
        this.redTurn = !this.redTurn;
    }


    public String show() {
        String border = "╚" + "═".repeat(this.base * 2 + 1) + "╝";

        // TODO Preguntar si quiere numeros o no
        String numbers = "  " + IntStream.rangeClosed(1, this.base)
                .mapToObj(i -> String.valueOf(i % 10))
                .collect(Collectors.joining(" "))
                + "\n";


        String boardContent = this.board.stream()
                .map(row -> "║ " + row.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ")) + " ║")
                .collect(Collectors.joining("\n"));

        return "\n" + boardContent + "\n" + border + "\n" + numbers;
    }
}