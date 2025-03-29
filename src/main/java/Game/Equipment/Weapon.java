package Game.Equipment;

public class Weapon extends Equipment {

    /**
     * Constructor for a weapon, weapon can have attack, health and agility stats.
     * @param name Name of the weapon.
     * @param attack Attack value the weapon possesses.
     * @param health Health value the weapon possesses.
     * @param agility Agility value the weapon possesses.
     */
    public Weapon(String name, int attack, int health, int agility) {
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.agility = agility;
        this.defense = 0;
    }
}
