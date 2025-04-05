package game.characters;

import static storage.Storage.SAVE_DELIMITER;
import exceptions.RolladieException;
import game.currency.Gold;
import game.equipment.Equipment;
import game.equipment.EquipmentList;


/**
 * A class to create the player object.
 */
public class Player extends Character {

    private static final String PLAYER_MODEL =
            "      __      _\n" +
                    "     /__\\__  //\n" +
                    "    //_____\\///\n" +
                    "   _| /-_-\\)|/_\n" +
                    "  (___\\ _ //___\\\n" +
                    "  (  |\\\\_/// * \\\\\n" +
                    "   \\_| \\_((*   *))\n" +
                    "   ( |__|_\\\\  *//\n" +
                    "   (o/  _  \\_*_/\n" +
                    "   //\\__|__/\\\n" +
                    "  // |  | |  |\n" +
                    " //  _\\ | |___)\n" +
                    "//  (___|";

    private Gold gold;
    private int attackBonus;
    private int defenseBonus;
    private EquipmentList equipments;

    /**
     * Constructor to construct a player object.
     *
     * @param health an integer array to represent the health bar of player.
     * @param attack an integer to represent the damage value in an attack.
     * @param defense an integer to represent the defense value.
     * @param name a string to represent the name of the player.
     */
    public Player(int[] health, int attack, int defense, String name) {
        super(health, attack, defense, name);
        this.gold = new Gold(0);
        this.equipments = new EquipmentList();
    }

    /**
     * Overloaded Constructor to construct a player object with defined parameters
     * Usage within Storage Class for loading game
     * @param healthBars
     * @param attackValue
     * @param defenseValue
     * @param characterName
     * @param maxHealth
     */
    public Player(int[] healthBars, int attackValue, int defenseValue, String characterName, int maxHealth, int amount, EquipmentList equipments) {
        super(healthBars, attackValue, defenseValue, characterName, maxHealth);
        this.gold = new Gold(amount);
        this.equipments = equipments;
    }

    public Player(int healthBars, int attackValue, int defenseValue, String characterName, int maxHealth, int amount, EquipmentList equipments) {
        super(healthBars, attackValue, defenseValue, characterName);
        this.gold = new Gold(amount);
        this.equipments = equipments;
    }

    /**
     * Gold amount earned by player
     * @param earnedGold An integer to represent the amount earned.
     */
    public void earnGold(Gold earnedGold) {
        this.gold = this.gold.earnGold(earnedGold);
    }

    /**
     * Gold amount spent by player.
     * @param spentGold An integer to represent the amount spent.
     */

    public void spendGold(Gold spentGold) throws RolladieException {
        this.gold = this.gold.spendGold(spentGold);
    }

    public Gold getGold() {
        return gold;
    }

    public boolean hasEnoughGold(int amount) {
        return getGold().getAmount() >= amount;
    }

    public void buyEquipment(Equipment equipment) throws RolladieException {
        this.equipments = this.equipments.addEquipment(equipment);
        spendGold(new Gold(equipment.getValue()));
    }

    public void sellEquipment(String equipmentType) throws RolladieException {
        earnGold(new Gold(equipments.getEquipment(equipmentType).getValue() / 2));
        this.equipments = this.equipments.removeEquipment(equipmentType);
    }

    public Equipment getEquipment(String equipmentType) throws RolladieException {
        return this.equipments.getEquipment(equipmentType);
    }

    /**
     * Get the defense value of player
     *
     * @return current defenseValue depending on whether player used DefendAction the previous turn
     */
    @Override
    public int getDefenseValue() {
        if (isDefending) {
            return (defenseValue + defenseBonus + equipments.getEquipmentDefense()) * 3;
        }
        return defenseValue + defenseBonus + equipments.getEquipmentDefense();
    }

    /**
     * Return the character's attack value.
     *
     * @return an integer representing the character's attack value.
     */
    public int getAttackValue() {
        return attackValue + attackBonus + equipments.getEquipmentAttack();
    }

    /**
     * Set player's attack bonus value.
     *
     * @param bonus dice outcome for attack action.
     */
    public void setAttackBonus(int bonus){
        attackBonus = bonus;
    }

    /**
     * Set player's defend bonus value.
     *
     * @param bonus dice outcome for defend action.
     */
    public void setDefenseBonus(int bonus){
        defenseBonus = bonus;
    }


    /**
     * Return the information of a player.
     *
     * @return A string that show information of a player.
     */
    @Override
    public String toString() {
        return super.toString() +  "\nEquipments: "
                + equipments.toString();
    }

    /**
     * Returns the encoded string of player attributes to be saved
     * @return encoded string
     */
    @Override
    public String toText() {
        return super.toText() + SAVE_DELIMITER +
                this.gold.getAmount() + SAVE_DELIMITER +
                this.equipments.toText();
    }
}
