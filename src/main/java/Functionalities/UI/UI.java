package Functionalities.UI;

import Game.Characters.Character;
import Game.Characters.Player;

/**
 * The UI class is responsible for handling the user interface in the text-based RPG game.
 * It displays messages related to the game, player, and enemy interactions, and renders the game's logo.
 */
public class UI {
    /**
     * A separator string used to format the output in the UI.
     */
    public static String lineSeparator = "======================================================================";



    /**
     * A string containing the ASCII art logo of the game.
     */
    public static String logo =
        " ____    " + "  ___   " + " _      " + " _      " + "    _    " + " ____   " + " ___  " + " _____  " + "\n" +
        "|  _ \\   " + " / _ \\  " + "| |     " + "| |     " + "   / \\   " + "|  _ \\  " + "|_ _| " + "| ____| " + "\n" +
        "| |_) |  " + "| | | | " + "| |     " + "| |     " + "  / _ \\  " + "| | | | " + " | |  " + "|  _|   " + "\n" +
        "|  _ <   " + "| |_| | " + "| |___  " + "| |___  " + " / /_\\ \\ " + "| |_| | " + " | |  " + "| |___  " + "\n" +
        "|_| \\_\\  " + " \\___/  " + "|_____| " + "|_____| " + "/_/   \\_\\" + "|____/  " + "|___| " + "|_____| ";


    public static String dieFace1 =
        " _______ \n" +
        "|       |\n" +
        "|   O   |\n" +
        "|       |\n" +
        " ------- \n";

    public static String dieFace2 =
        " _______ \n" +
        "| O     |\n" +
        "|       |\n" +
        "|     O |\n" +
        " ------- \n";

    public static String dieFace3 =
        " _______ \n" +
        "| O     |\n" +
        "|   O   |\n" +
        "|     O |\n" +
        " ------- \n";

    public static String dieFace4 =
        " _______ \n" +
        "| O   O |\n" +
        "|       |\n" +
        "| O   O |\n" +
        " ------- \n";

    public static String dieFace5 =
        " _______ \n" +
        "| O   O |\n" +
        "|   O   |\n" +
        "| O   O |\n" +
        " ------- \n";

    public static String dieFace6 =
        " _______ \n" +
        "| O   O |\n" +
        "| O   O |\n" +
        "| O   O |\n" +
        " ------- \n";



    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }


    /**
     * Prints a welcome message to the player, including the game logo and a description.
     */
    public static void printWelcomeMessage() {
        System.out.println("Welcome to");
        System.out.println(lineSeparator);
        System.out.println(logo);
        System.out.println(lineSeparator);
        System.out.println("A text-based RPG game where your fate is determined by the roll of a die!!");
    }


    public static void printCharacterInfo(Character c) {
        System.out.println(c);
    }

    public static void printPlayerEarnGold(Player player, int amount) {
        System.out.println("You gained " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
    }

    public static void printPlayerSpentGold(Player player, int amount) {
        System.out.println("You spent " + amount + " gold. You now have a total of " + player.getGold() + " gold.");
    }

    public static void printExitMessage() {
        System.out.println("Narrator: Leaving so soon? I expected more from you!");
    }

    public static void printDeathMessage() {
        System.out.println("Narrator: Game over, you've died! L");
    }
}

