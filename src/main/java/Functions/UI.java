package Functions;

import Characters.Players.Player;
import Game.Game;

import java.util.Scanner;

/**
 * The UI class is responsible for handling the user interface in the text-based RPG game.
 * It displays messages related to the game, player, and enemy interactions, and renders the game's LOGO.
 */
public class UI {
    /**
     * A separator string used to format the output in the UI.
     */
    public static final String LINE_SEPARATOR = "=====================================================================";

    private static final Scanner SCANNER = new Scanner(System.in);


    public static String readInput() {
        String inputLine = SCANNER.nextLine().toLowerCase();
        return inputLine;
    }

    public static int readIntegerInput() throws InterruptedException {
        String input = SCANNER.nextLine().trim();
        int intInput = -1;
        try {
            intInput = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
            Thread.sleep(1000);
        }
        return intInput;
    }

    /**
     * A string containing the ASCII art LOGO of the game.
     */
    public static final String LOGO =
            " ____    " + "  ___   " + " _      " + " _      " + "    _    " + " ____   " + " ___  " + " _____  " + "\n" +
            "|  _ \\ " + "   / _ \\  " + "| |     " + "| |     " + "   / \\   " + "|  _ \\  " + "|_ _| " + "| ____| " + "\n" +
            "| |_) |  " + "| | | | " + "| |     " + "| |     " + "  / _ \\  " + "| | | | " + " | |  " + "|  _|   " + "\n" +
            "|  _ <   " + "| |_| | " + "| |___  " + "| |___  " + " / /_\\ \\ " + "| |_| | " + " | |  " + "| |___  " + "\n" +
            "|_| \\_\\  " + " \\___/  " + "|_____| " + "|_____| " + "/_/   \\_\\" + "|____/  " + "|___| " + "|_____| ";
    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }


    /**
     * Prints a welcome message to the player, including the game LOGO and a description.
     */
    public static void printWelcomeMessage() {
        System.out.println("Welcome to");
        System.out.println(LINE_SEPARATOR);
        System.out.println(LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println("A text-based RPG game where your fate is determined by the roll of a die!!");
    }

    public static void printExitMessage() {
        System.out.println("Narrator: Leaving so soon? I expected more from you!");
    }

    public static void printDeathMessage() {
        System.out.println("Narrator: Game over, you've died! L");
    }

    public static void printWinMessage() {
        System.out.println("Narrator: YOU ARE BIG BOI");
    }

    public static String promptSaveFile() {
        System.out.print("Choose save slot to load (1–3): ");
        return readInput();
    }

    public static void printOptions() {
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");
    }

    public static void showContinueScreen(Game game) {
        Player player = game.getPlayer();
        int wave = game.getWave();
        System.out.println(player.toString());

        System.out.println("🌊 Current Wave: " + wave);
        System.out.println("\nPress Enter to continue...");
        SCANNER.nextLine();
    }

}
