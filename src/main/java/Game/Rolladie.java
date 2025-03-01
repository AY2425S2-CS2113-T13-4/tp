package Game;

import java.util.Arrays;
import java.util.Scanner;
import Functionalities.UI;
import Game.Characters.Enemy;
import Game.Characters.Player;

public class Rolladie {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        UI.printWelcomeMessage();
        int[] health1 = {100};
        int[] health2 = {50};
        Player player = new Player(health1, 10, 10, "Player");
        Enemy enemy = new Enemy(health2, 10, 10, "Enemy");
        System.out.println(Arrays.toString(enemy.getHealthBars()));
        player.attack(enemy);
        System.out.println(Arrays.toString(enemy.getHealthBars()));
    }
}
