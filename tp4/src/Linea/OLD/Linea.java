package Linea.OLD;

public class Linea {

    public static char RED_CHAR = 'X';
    public static char BLUE_CHAR = 'O';

    private int base;
    private int height;

    // Char[filas][columnas]
    private char[][] board;
    private char gamemode;
    private boolean finished = false;

    private boolean redTurn = true;

    public Linea(int base, int height, char gamemode) {
        if (base < 4 || height < 4) {
            throw new IllegalArgumentException("Base and height must be greater than 3");
        }
        if (gamemode != 'C' && gamemode != 'A' && gamemode != 'B') {
            throw new IllegalArgumentException("Gamemode must be A b c");
        }

        this.base = base;
        this.height = height;
        this.gamemode = gamemode;
        this.board = new char[height][base];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < base; j++) {
                board[i][j] = '-';
            }
        }
    }

    public char getPosition(int x, int y) {
        return board[y][x];
    }

    public void playRedAt(int column) {
        column--;
        if (column < 0 || column >= base) {
            throw new IllegalArgumentException("Column must be between 1 and " + (base));
        }
        if (board[0][column] != '-') {
            throw new IllegalArgumentException("Column " + column + " is full");
        }

        int row = 0;
        while (row < height && board[row][column] == '-') {
            row++;
        }
        board[row - 1][column] = RED_CHAR;
        redTurn = false;
    }

    public void playBlueAt(int prompt) {

    }

    public boolean finished() {
        return finished;
    }

    public boolean isRedTurn() {
        return redTurn;
    }

    public String show() {
        String result = "";

        for (int i = 0; i < height; i++) {
            result += "\n║";
            for (int j = 0; j < base; j++) {
                result += board[i][j];
            }
            result += "║";
        }

        return result + "\n╚═══════════════════╝"; // despues ver de que respete el tamaño de la base
    }
}
