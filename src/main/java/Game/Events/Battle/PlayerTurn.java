package Game.Events.Battle;

import Functionalities.Parser;
import Functionalities.UI;
import Game.Actions.Action;
import Game.Actions.BattleAction.AttackAction;
import Game.Actions.BattleAction.DefendAction;
import Game.Actions.BattleAction.FleeAction;
import Game.Characters.Character;

import static Functionalities.Parser.getAction;

/**
 * Represents the player's turn. Player can select different moves during his/her turn.
 */
public class PlayerTurn extends Turn {
    private final Character player;
    private final Character enemy;


    /**
     * Constructs a PlayerTurn object to represent current player's turn.
     * Clears isDefending buff on player to prevent stacking defense.
     *
     * @param player The player character in battle.
     * @param enemy The enemy character in battle.
     */
    public PlayerTurn(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
        if (player.getDefending()) {
            player.setDefending(false);
        }
    }

    /**
     * Uses parser to scan for user input to carry out an action.
     *
     * @return A String representing the action of the player.
     */
    private Action getCurrAction() {
        String inputString = Parser.readInput();
        return getAction(inputString);
    }

    /**
     * Handles the user input during user's turn
     *
     */
    public void handleAction() {
        Action currentAction = getCurrAction();
        if (currentAction instanceof AttackAction) {
            player.attack(enemy);
        }
        else if (currentAction instanceof DefendAction) {
            player.setDefending(true);
        }
        else if (currentAction instanceof FleeAction) {
            hasSurrendered = true;
        }

    }
}
