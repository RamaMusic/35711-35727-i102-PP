package Linea;
public class GameInterface {

    public static void main(String[] args) throws Exception {

        System.out.println("Dimensiones?");

        Linea game = new Linea(promptAsInt("Base? "),

                promptAsInt("Altura? "),

                promptAsChar("Estrategia de Juego: A, B o C? "));

        System.out.println(game.show());

        while (!game.finished()) {

            game.playRedAt(promptAsInt("Rojas? "));

            System.out.println(game.show());

            if (!game.finished()) {

                game.playBlueAt(promptAsInt("Azul? "));

                System.out.println(game.show());

            }

        }

    }

    private static int promptAsInt(String message) {

        System.out.print(message);

        return Integer.parseInt(System.console().readLine());

    }

    private static char promptAsChar(String message) {

        System.out.print(message);

        return System.console().readLine().charAt(0);

    }

}