package Game.Battle;

import Exceptions.RolladieException;
import Game.Rolladie;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import Game.Events.Battle.Battle;
import Game.Events.Battle.BattleLogic;
import Game.Characters.Player;
import Game.Characters.Enemy;
import Game.Events.Battle.PlayerTurn;


public class BattleTest {
    @Test
    public void attack_reduceHealth_success() {
        Player player = new Player(new int[]{100}, 10, 0, "player");
        Enemy enemy = new Enemy(new int[]{100}, 5, 0, "enemy");

        //Enemy health decreases when attacked.
        player.attack(enemy);
        int enemyHealth = enemy.getHealthBars()[0];
        assertEquals(90, enemyHealth);

        //Player health decreases when attacked.
        enemy.attack(player);
        int playerHealth = player.getHealthBars()[0];
        assertEquals(95, playerHealth);
    }

    @Test
    public void battleExits_whenHPIsZero() {
        Player player = new Player(new int[]{100}, 10, 0, "player");
        Enemy enemy = new Enemy(new int[]{10}, 10, 0, "enemy");
        BattleLogic battleLogic = new BattleLogic(player, enemy);

        //Enemy health should be zero, triggering end of battle
        player.attack(enemy);
        battleLogic.checkBattleEnd(new PlayerTurn(player, enemy));
        assertTrue(battleLogic.getHasBattleEnded());

        //Player wins as enemy health is zero
        assertTrue(battleLogic.getHasWon());
    }

    @Test
    public void battleExits_whenSurrender() {
        Player player = new Player(new int[]{100}, 10, 0, "player");
        Enemy enemy = new Enemy(new int[]{10}, 10, 0, "enemy");
        BattleLogic battleLogic = new BattleLogic(player, enemy);
        PlayerTurn playerTurn = new PlayerTurn(player, enemy);
        playerTurn.hasSurrendered = true;
        battleLogic.checkBattleEnd(playerTurn);

        //Battle ends when surrendering.
        assertTrue(battleLogic.getHasBattleEnded());
        //Battle is lost.
        assertFalse(battleLogic.getHasWon());
    }

    @Test
    public void invalidBattle_HPIsZero_ExceptionThrown() {
        //Player hp initialised to zero.
        try {
            Battle battle = new Battle(new Player(new int[]{0}, 10, 0, "p"), new Enemy(new int[]{10}, 10, 0, "e"));
            fail();
        }
        catch (RolladieException e) {
            assertEquals("Player health bar is empty.", e.getMessage());
        }
        //Enemy hp initialised to zero.
        try {
            Battle battle = new Battle(new Player(new int[]{10}, 10, 0, "p"), new Enemy(new int[]{0}, 10, 0, "e"));
            fail();
        }
        catch (RolladieException e) {
            assertEquals("Enemy health bar is empty.", e.getMessage());
        }
    }
}
