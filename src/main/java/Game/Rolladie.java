package Game;

import Exceptions.RolladieException;
import Functionalities.Parser;
import Functionalities.Storage;
import Functionalities.UI;


public class Rolladie {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) throws RolladieException {
        assert false : "dummy assertion set to fail";

        UI.printWelcomeMessage();
        String inputString = Parser.readInput();
        while (!inputString.equals("exit")) {
            if (inputString.equals("start")) {
                Game game = new Game();
                game.run();
                UI.printWelcomeMessage();
            } else if (inputString.equals("load")) {
                Game game = Storage.loadGame();
                game.run();
                UI.printWelcomeMessage();
            } else {
                UI.printMessage("You can only use \"start\" or \"load\" bro");
            }
            inputString = Parser.readInput();
        }
        UI.printMessage("Leaving so soon? I expected more from you!");
    }
}
