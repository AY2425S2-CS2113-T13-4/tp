package Game.Equipment;

import java.util.ArrayList;

/**
 * EquipmentList class to store information on the equipment that the player is using
 */
public class EquipmentList {
    private ArrayList<Equipment> equipmentList;
    private int maxNumberOfEquipment;
    private int totalAttack;
    private int totalDefense;
    private int totalHp;
    private int totalAgility;

    public EquipmentList(int maxNumberOfEquipment) {
        this.maxNumberOfEquipment = maxNumberOfEquipment;
        equipmentList = new ArrayList<>();
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }
    public void removeEquipment(Equipment equipment) {
        equipmentList.remove(equipment);
    }

}
