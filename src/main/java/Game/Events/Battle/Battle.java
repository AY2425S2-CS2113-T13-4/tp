package Game.Events.Battle;

import Exceptions.RolladieException;
import Functionalities.UI;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Events.Event;

import static Functionalities.Storage.SAVE_DELIMITER;

/**
 * Represents a battle event in which player fights an enemy.
 */
public class Battle extends Event {
    private final Enemy enemy;
    private boolean hasWon;

    /**
     * Constructs a valid Battle event object.
     * Initialises hasWon to be false at start of battle.
     * @param player The player character in battle.
     * @param enemy The enemy character that player is facing.
     */
    public Battle(Player player, Enemy enemy) throws RolladieException {
        super(player);
        final int FIRST_HEALTH_BAR = 0;
        if (player.getHealthBars()[FIRST_HEALTH_BAR] <= 0) {
            throw new RolladieException("Player health bar is empty.");
        } else if (enemy.getHealthBars()[FIRST_HEALTH_BAR] <= 0) {
            throw new RolladieException("Enemy health bar is empty.");
        }
        if (player.getAttackValue() < 0) {
            throw new RolladieException("Player attack value is negative.");
        } else if (enemy.getAttackValue() < 0) {
            throw new RolladieException("Enemy attack value is negative.");
        }
        this.enemy = enemy;
        hasWon = false;
    }

    public Battle(Player player) {
        super(player);
        int[] enemyHealth = {50};
        this.enemy = new Enemy(enemyHealth, 15, 5, "Goblin");
        this.hasWon = false;
    }

    @Override
    public void run() {
        UI.battleEntry(this.enemy);
        startBattle();
        UI.battleExit(this.enemy, this.player);
    }

    @Override
    public String getEventIcon() {
        return "Battle";
    }

    @Override
    public String toText() {
        return this.getEventIcon() + SAVE_DELIMITER +
                this.enemy.toText();
    }

    /**
     * Starts the battle.
     * When battle starts, creates a BattleLogic object to handle battle logic.
     *
     * @return A boolean representing whether the player has won the battle.
     */
    public boolean startBattle() {
        BattleLogic battleLogic = new BattleLogic(player, enemy);
        hasWon = battleLogic.BattleSequence();

        return hasWon;
    }
}
