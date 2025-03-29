package Game.Equipment;

public class Boots extends Equipment{

    /**
     * Constructor for a boots equipment. Boots can have defense and agility values.
     * @param name Name of the boots.
     * @param defense Defense value associated with boots.
     * @param agility Agility value associated with boots.
     */
    public Boots(String name, int defense, int agility) {
        this.name = name;
        this.defense = defense;
        this.agility = agility;
        this.attack = 0;
        this.health = 0;
    }
}
