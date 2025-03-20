package Game.Characters;

import java.util.Arrays;

/**
 * A class to represent characters in the game.
 */
public class Character {
    private int[] healthBars;
    private int attackValue;
    private int defenseValue;
    private String characterName;
    public boolean isAlive;
    public int currentHealthIndex = 0;
    private final int maxHealth;

    /**
     * Construct a Character object.
     *
     * @param health an array to represent the character's health bar.
     * @param attack attack value of the character.
     * @param defense defense value of the character.
     * @param name the name of the character.
     */
    public Character(int[] health, int attack, int defense, String name) {
        assert health.length > 0 : "Health bar array must not be empty";
        assert attack >= 0 : "Attack value must be non-negative";
        assert defense >= 0 : "Defense value must be non-negative";
        assert !name.isEmpty() : "Character name must not be empty";
        healthBars = health;
        attackValue = attack;
        defenseValue = defense;
        characterName = name;
        isAlive = true;
        maxHealth = healthBars[0];
    }

    /**
     * Return the character's name.
     *
     * @return a string showing the character's name.
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Return the character's health bar.
     *
     * @return integer array representing the character's health.
     */
    public int[] getHealthBars() {
        return healthBars;
    }

    /**
     * Return the character's attack value.
     *
     * @return an integer representing the character's attack value.
     */
    public int getAttackValue() {
        return attackValue;
    }

    /**
     * Return the character's defense value.
     *
     * @return an integer representing the character's defense value.
     */
    public int getDefenseValue() {
        return defenseValue;
    }

    /**
     * Return the character's current status.
     * @return a boolean to indicate if the character is still alive.
     */
    public boolean getCurrentStatus() {
        return isAlive;
    }

    /**
     * Performs an attack on the defender.
     *
     * @param defender character being attacked in the attack event.
     */
    public void attack(Character defender) {
        assert defender != null: "Defender must not be null";
        assert defender.isAlive : "Defender must be alive";

        int damage = calculateDamage(defender);
        defender.takeDamage(damage);
        System.out.println(this.characterName + " attacks " + defender.getCharacterName() +
                " for " + damage + " damage.");

    }

    /**
     * Returns the calculated amount of damage dealt to the defender
     * based on the attacker's attack value and the defender's defense value.
     *
     * @param defender character being attacked in this attack event.
     * @return an integer representing the damage value imposed on defender.
     */
    public int calculateDamage(Character defender){
        assert defender != null: "Defender must not be null";
        assert defender.isAlive : "Defender must be alive";

        double damageReduction = (double) 100 / (100 + defender.defenseValue);
        int damageTaken = (int) (this.attackValue * damageReduction);
        return damageTaken;

    }

    /**
     * Apply damage to the character by updating its health bar.
     * Case 1: character is considered not alive if all the health bars are depleted.
     * Case 2: if the current health bar is depleted, update the next available health bar.
     *
     * @param damage an integer representing the damage value.
     */
    public void takeDamage(int damage) {
        assert damage > 0: "Damage must be non-negative";

        int remainingDamage = damage;
        while (remainingDamage > 0) {

            /* Case 1 */
            if (currentHealthIndex >= healthBars.length) {
                isAlive = false;
                break;
            }
            /* Case 2 */
            if (healthBars[currentHealthIndex] - remainingDamage <= 0) {
                remainingDamage -= healthBars[currentHealthIndex];
                healthBars[currentHealthIndex] = 0;
                currentHealthIndex++;
            }
            else {
                healthBars[currentHealthIndex] -= remainingDamage;
                break;
            }
        }
    }

    @Override
    public String toString() {
        String animatedHealthBars = "";
        for (int health : healthBars) {
            animatedHealthBars += "[";
            for (int i = 0; i < health; i++) {
                animatedHealthBars += "#";
            }
            for (int i = health; i < maxHealth; i++) {
                animatedHealthBars += "_";
            }
            animatedHealthBars += "]";
        }

        return this.characterName + "'s stats:" +
                "\nHP: \t" + animatedHealthBars +
                "\nATK:\t" + this.attackValue +
                "\nDEF:\t" + this.defenseValue;
    }

}
