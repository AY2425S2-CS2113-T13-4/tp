/*
 * This source file was generated by the Gradle 'init' task
 */

import Characters.Abilities.Ability;
import Characters.Players.Player;
import Equipment.DragonShield;
import Equipment.EquipmentList;
import Equipment.FlamingSword;
import Functions.Storage;
import Game.Game;
import exceptions.RolladieException;

import Functions.UI;
import UI.BattleDisplay;

import static UI.BattleDisplay.drawPowerBar;

public class Rolladie {

    public static void main(String[] args) throws InterruptedException, RolladieException {
        mainMenu();
    }

    /**
     * Starts the game menu and shows options for new game or loading from save
     */
    public static void mainMenu() throws InterruptedException {
        UI.printWelcomeMessage();

        UI.printOptions();
        String userInput = UI.readInput();
        Game game;
        while(!userInput.equals("3")) {
            try {
                switch (userInput) {
                case "1":
                    game = new Game();
                    break;
                case "2":
                    int saveSlot = Integer.parseInt(UI.promptSaveFile());
                    try {
                        game = Storage.loadGame(saveSlot);
                        UI.printMessage("✅ Game loaded from save slot " + saveSlot);
                        UI.showContinueScreen(game);
                    } catch (RolladieException e) {
                        UI.printErrorMessage(e.getMessage());
                        UI.printMessage("Starting new game instead.");
                        game = new Game();
                    }
                    break;
                default:
                    throw new RolladieException("Invalid option");
                }
                game.run();
                UI.printWelcomeMessage();
                UI.printOptions();
            } catch (RolladieException e) {
                UI.printErrorMessage(e.getMessage());
            } finally {
                userInput = UI.readInput();
            }
        }
        UI.printExitMessage();
    }
}