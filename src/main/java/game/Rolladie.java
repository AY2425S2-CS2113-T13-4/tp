package game;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import exceptions.RolladieException;
import game.characters.Player;
import game.currency.Gold;
import game.equipment.ArmorDatabase;
import game.equipment.Equipment;
import game.equipment.EquipmentList;
import storage.Storage;
import functionalities.ui.UI;
import game.menu.MenuSystem;
import game.menu.MenuSystem.MenuAction;
import game.menu.TerminalUtils;

/**
 * Entry point of the Rolladie application
 * Initializes the main menu and starts interactions with the user
 */

public class Rolladie {

    public static void main(String[] args) {
        // assert false : "dummy assertion set to fail";

        try {
            TerminalUtils.saveTerminalState(); // Before launching Lanterna
            MenuSystem menuSystem = new MenuSystem();
            menuSystem.enterPrivateMode();
            
            Map<String, MenuAction> mainMenu = new LinkedHashMap<>();

            mainMenu.put("Start Game", () -> { 
                Game game = new Game();
                gameStart(game, menuSystem);
                return false; });
            mainMenu.put("Load Game", () -> { 
                try {
                    Game game = Storage.loadGame(); // May throw RolladieException
                    gameStart(game, menuSystem);
                } catch (RolladieException e) {
                    UI.printErrorMessage("Failed to load game: " + e.getMessage());
                    return true;
                }
                return false; });
            mainMenu.put("Exit", () -> {
                UI.printExitMessage();
                System.exit(0);
                return true;
            });

            menuSystem.displayMenu("Main Menu", mainMenu);

        } catch (IOException | InterruptedException e) {
            UI.printErrorMessage(e.getMessage());
        }
    }

    private static void gameStart(Game game, MenuSystem menuSystem) {
        try {
            menuSystem.exitPrivateMode();
            TerminalUtils.resetTerminal();
            // Ensure console input is visible
            System.out.print("\033[?25h"); // Show cursor (Unix)
            System.out.flush();

            game.run();

            // Prepare for returning to Lanterna
            TerminalUtils.prepareForLanterna();
            
            menuSystem.reinitializeTerminal(); // Add this method to MenuSystem
            menuSystem.enterPrivateMode();

        } catch (IOException | InterruptedException e) {
            UI.printErrorMessage(e.getMessage());

            try {
                TerminalUtils.resetTerminalOverkill();
            } catch (Exception exception) {
                UI.printErrorMessage("Error while resetting terminal overkill: " + exception.getMessage());

            }
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }


}
