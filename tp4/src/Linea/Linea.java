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

    private char gameMode;

    private List<List<Character>> board;

    private boolean redTurn = true;

    private boolean finished = false;


    public Linea(int base, int height, char gameMode) {

        if (base < 4 || height < 4) {
            throw new RuntimeException("Base and height must be greater than 3");
        }

        //TODO cuando hagamos la clase gamemode esto cambia a un contains.
        if (gameMode != 'C' && gameMode != 'A' && gameMode != 'B') {
            throw new RuntimeException("Gamemode must be A, B or C");
        }

        this.base = base;
        this.height = height;
        this.gameMode = gameMode;

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
            throw new RuntimeException("It's not red's turn");
        }

        stackSlot(column, redSlot);
        return this;
    }

    public Linea playBlueAt(int column) {
        if ( !this.isBlueTurn() ) {
            throw new RuntimeException("It's not blue's turn");
        }

        stackSlot(column, blueSlot);
        return this;
    }

    private void stackSlot(int column, char slotSymbol) {

        if (this.finished) {
            throw new RuntimeException("Game is finished");
        }

        if (column > this.base || column < 1) {
            throw new RuntimeException("Column must be between 1 and " + this.base);
        }

        if (this.board.get(0).get(column - 1) != emptySlot) {
            throw new RuntimeException("Column " + column + " is full");
        }

        int row = IntStream.range(0, this.height)
                .filter(r -> this.board.get(r).get(column - 1) == emptySlot)
                .reduce((first, second) -> second)
                .orElse(this.height - 1);

        this.board.get(row).set(column - 1, slotSymbol);
        finished = checkVictory(slotSymbol);
        this.redTurn = !this.redTurn;
    }


    public String show() {
        String border = "╚" + "═".repeat(this.base * 2 + 1) + "╝";

        // TODO Preguntar si quiere números o no
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

    private boolean checkVictory(char slotSymbol) {
        if (gameMode == 'A') {
            return checkHorizontalVictory(slotSymbol) || checkVerticalVictory(slotSymbol);
        } else if (gameMode == 'B') {
            return checkDiagonalVictory(slotSymbol);
        } else {
            return checkHorizontalVictory(slotSymbol) || checkVerticalVictory(slotSymbol) || checkDiagonalVictory(slotSymbol);
        }
    }

    private boolean checkHorizontalVictory(char slotSymbol) {
        return IntStream.range(0, this.height)
                .anyMatch(row -> IntStream.range(0, this.base - 3)
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> this.board.get(row).get(column + i) == slotSymbol)));
    }

    private boolean checkVerticalVictory(char slotSymbol) {
        return IntStream.range(0, this.base)
                .anyMatch(column -> IntStream.range(0, this.height - 3)
                        .anyMatch(row -> IntStream.range(0, 4)
                                .allMatch(i -> this.board.get(row + i).get(column) == slotSymbol)));
    }

    private boolean checkDiagonalVictory(char slotSymbol) {
        return IntStream.range(0, this.height - 3)
                .anyMatch(row -> IntStream.range(0, this.base - 3)
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> this.board.get(row + i).get(column + i) == slotSymbol)))
                || IntStream.range(0, this.height - 3)
                .anyMatch(row -> IntStream.range(3, this.base)
                        .anyMatch(column -> IntStream.range(0, 4)
                                .allMatch(i -> this.board.get(row + i).get(column - i) == slotSymbol)));
    }

    }