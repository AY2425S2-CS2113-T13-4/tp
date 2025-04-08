package functions.UI;

/**
 * GameOverUI class to print the end screens for the player.
 */
public class GameOverUI {
    public static final String GAMEOVER =
                        "   _____                         ____                 \n" +
                        "  / ____|                       / __ \\                \n" +
                        " | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ \n" +
                        " | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|\n" +
                        " | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   \n" +
                        "  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   \n" +
                        "                                                     ";
    public static final String VICTORY =
            "▓██   ██▓ ▒█████   █    ██     █     █░ ▒█████   ███▄    █\n" + 
    "▒██  ██▒▒██▒  ██▒ ██  ▓██▒   ▓█░ █ ░█░▒██▒  ██▒ ██ ▀█   █\n" + 
     "▒██ ██░▒██░  ██▒▓██  ▒██░   ▒█░ █ ░█ ▒██░  ██▒▓██  ▀█ ██▒\n" +
     "░ ▐██▓░▒██   ██░▓▓█  ░██░   ░█░ █ ░█ ▒██   ██░▓██▒  ▐▌██▒\n" +
     "░ ██▒▓░░ ████▓▒░▒▒█████▓    ░░██▒██▓ ░ ████▓▒░▒██░   ▓██░\n" +
      "██▒▒▒ ░ ▒░▒░▒░ ░▒▓▒ ▒ ▒    ░ ▓░▒ ▒  ░ ▒░▒░▒░ ░ ▒░   ▒ ▒ \n" +
    "▓██ ░▒░   ░ ▒ ▒░ ░░▒░ ░ ░      ▒ ░ ░    ░ ▒ ▒░ ░ ░░   ░ ▒░\n" +
    "▒ ▒ ░░  ░ ░ ░ ▒   ░░░ ░ ░      ░   ░  ░ ░ ░ ▒     ░   ░ ░ \n" +
    "░ ░         ░ ░     ░            ░        ░ ░           ░ \n" +
    "░ ░                                                        \n" +
            "              YOU WIN!                \n";

    public static void printGameOver() {
        System.out.println(GAMEOVER);
    }
    public static void printVictory() {
        System.out.println(VICTORY);
    }
}
