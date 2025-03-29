package Functionalities;

import Game.Characters.Player;
import Game.Characters.Enemy;
import Game.Characters.Character;

import java.util.Scanner;

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
     * A placeholder string for the player's model, which can be used for player-related information.
     */
    public static String playerModel =
            "      __      _\n" +
                    "     /__\\__  //\n" +
                    "    //_____\\///\n" +
                    "   _| /-_-\\)|/_\n" +
                    "  (___\\ _ //___\\\n" +
                    "  (  |\\\\_/// * \\\\\n" +
                    "   \\_| \\_((*   *))\n" +
                    "   ( |__|_\\\\  *//\n" +
                    "   (o/  _  \\_*_/\n" +
                    "   //\\__|__/\\\n" +
                    "  // |  | |  |\n" +
                    " //  _\\ | |___)\n" +
                    "//  (___|";

    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
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

    /**
     * Prints information about the player.
     *
     * @param player The player whose information is to be printed.
     */
    public static void printPlayerInfo(Player player) {
        System.out.println(playerModel);
    }

    /**
     * Prints a message announcing the arrival of an enemy during battle.
     *
     * @param enemy The enemy the player is about to face.
     */
    public static void battleEntry(Enemy enemy) {
        System.out.println("A villainous " + enemy.getCharacterName() + " stands in your way!");
    }

    /**
     * Prints the start of a battle turn, displaying both the player's and enemy's information.
     * The information is printed with separators for clarity.
     *
     * @param player The player character involved in the battle.
     * @param enemy The enemy character involved in the battle.
     */
    public static void printBattleTurnStart(Character player, Character enemy) {
        System.out.println(lineSeparator);
        printCharacterInfo(player);
        System.out.println(lineSeparator);
        printCharacterInfo(enemy);
        System.out.println(lineSeparator);
    }

    /**
     * Prints a message after the battle, showing the outcome and player's health status.
     *
     * @param enemy The enemy that the player fought.
     * @param player The player involved in the battle.
     */
    public static void battleExit(Character player, Character enemy) {
        if (!player.isAlive) {
            System.out.println(enemy.getCharacterName() + " has defeated you. Another one bites the dust!");
            return;
        } else {
            System.out.println("You have vanquished " + enemy.getCharacterName());
        }

        if (player.getHealthBars()[0] > 90) {
            System.out.println("\"Ha! that was easy!\", you thought to yourself, unaware of greater dangers that lurk ahead...");
        } else if (player.getHealthBars()[0] > 50) {
            System.out.println("You dust yourself off, ready for whatever challenge comes your way...");
        } else {
            System.out.println("You stumble away from the battlefield, severely wounded...");
        }
    }

    public static void printPlayerAttack(Character player, Character enemy, int damage) {
        System.out.println("You punch the " + enemy.getCharacterName() + " with your bare fist!");
        if (damage > 30) {
            System.out.println("WHAMMM!! The " + enemy.getCharacterName() + " took a whopping " + damage + " damage!");
        } else if (damage > 10) {
            System.out.println("You dealt " + damage + " damage.");
        } else {
            System.out.println("That tickled the " + enemy.getCharacterName() + ". You dealt a measly " + damage + " damage.");
        }
    }

    public static void printPlayerDefend(Character player, Character enemy, int damage) {
        System.out.println("You raise your shield and brace yourself for the " + enemy.getCharacterName() + "'s attack");
        if (damage > 30) {
            System.out.println("Your defence was ineffective. You took " + damage + " damage!");
        } else if (damage > 10) {
            System.out.println("You blocked some of the damage, taking " + damage + " damage.");
        } else {
            System.out.println("Perfect block! You only took " + damage + " damage.");
        }
    }


    public static void printEnemyAttack(Character player, Character enemy, int damage) {
        System.out.println("The " + enemy.getCharacterName() + " lunges forward and attacks!");
        if (damage > 30) {
            System.out.println("Ouch!! The " + enemy.getCharacterName() + " nearly sends you flying! It dealt " + damage + " damage!");
        } else if (damage > 10) {
            System.out.println("It dealt " + damage + " damage.");
        } else {
            System.out.println("You barely felt that attack... the " + enemy.getCharacterName() + " dealt only " + damage + " damage.");
        }
    }

    public static void printCharacterInfo(Character c) {
        System.out.println(c.getCharacterName());
        System.out.print("Heath: ");
        int[] healthBars = c.getHealthBars();
        for (int health : healthBars) {
            int maxHealth = 100;
            System.out.print("[");
            for (int i = 0; i < health; i++) {
                System.out.print("#");
            }
            for (int i = health; i < maxHealth; i++) {
                System.out.print("_");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}

