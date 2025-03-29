package Game.Characters;

/**
 * A class to create the enemy object.
 */
public class Enemy extends Character {
    /**
     * Constructor to construct an enemy object.
     *
     * @param health an integer array to represent the health bar of enemy.
     * @param attack an integer to represent the damage value in an attack.
     * @param defense an integer to represent the defense value.
     * @param name a string to represent the name of the enemy.
     */

    public Enemy(int[] health, int attack, int defense, String name) {
        super(health, attack, defense, name);
    }

    public Enemy(int health, int attack, int defense, String name) {
        super(health, attack, defense, name);
    }

    public String getEnemyName() {
        return super.getCharacterName();
    }
}
