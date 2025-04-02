package Game.Equipment;

public class Armor extends Equipment {
    /**
     * Constructor for an armor piece. Armor can have health, defense and agility stats.
     * @param name Name of the armor
     * @param defense Defense Value associated with armor.
     * @param health Health Value associated with armor.
     * @param agility Agility Value associated with armor.
     */
    public Armor(String name, int defense, int health, int agility, int value) {
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.agility = agility;
        this.attack = 0;
        this.value = value;
    }

    @Override
    public String getEquipmentType() {
        return "armor";
    }

}
