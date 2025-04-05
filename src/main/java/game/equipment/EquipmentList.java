package game.equipment;

import exceptions.RolladieException;
import functionalities.ui.UI;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static storage.Storage.SAVE_DELIMITER;

/**
 * EquipmentList class to store information on the equipment that the player is using
 */
public class EquipmentList {
    private static final int MAX_EQUIPMENTS = 3;
    //player only allowed to equip one armour, one pair of boots and one weapon
    private static final int ARMOUR_SLOT = 0;
    private static final int BOOTS_SLOT = 1;
    private static final int WEAPON_SLOT = 2;

    private List<Optional<Equipment>> equipmentSlot;

    public EquipmentList() {
        equipmentSlot = List.of(Optional.empty(), Optional.empty(), Optional.empty());
    }

    public EquipmentList(List<Optional<Equipment>> equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public EquipmentList addEquipment(Equipment equipment) throws RolladieException {
        switch (equipment.getEquipmentType()) {
        case "armor":
            return new EquipmentList(addToEquipmentList(equipment, ARMOUR_SLOT));
        case "boots":
            return new EquipmentList(addToEquipmentList(equipment, BOOTS_SLOT));
        case "weapon":
            return new EquipmentList(addToEquipmentList(equipment, WEAPON_SLOT));
        default:
            throw new RolladieException("Invalid Equipment type!");
        }
    }

    private List<Optional<Equipment>> addToEquipmentList(Equipment equipment, int equipmentType)
            throws RolladieException {
        Optional<Equipment> currSlot = equipmentSlot.get(equipmentType);
        if (currSlot.isPresent()) {
            throw new RolladieException(currSlot.get().getEquipmentType() + " is already equipped!");
        }
        int count = 0;
        return IntStream.range(0,3)
                .mapToObj(x -> x == equipmentType ? Optional.of(equipment) : equipmentSlot.get(x))
                .toList();
    }

    public EquipmentList removeEquipment(String equipmentType) throws RolladieException {
        Equipment equipment = getEquipment(equipmentType);
        List<Optional<Equipment>> newSlot = equipmentSlot.stream()
                .map(e -> e.filter(x -> !x.equals(equipment)))
                .toList();
        return new EquipmentList(newSlot);
    }

    public Equipment getEquipment(String equipmentType) throws RolladieException {
        int currIndex;
        switch(equipmentType) {
        case "armor":
            currIndex = ARMOUR_SLOT;
            break;
        case "boots":
            currIndex = BOOTS_SLOT;
            break;
        case "weapon":
            currIndex = WEAPON_SLOT;
            break;
        default:
            throw new RolladieException("Invalid Equipment type!");
        }
        Optional<Equipment> currSlot = equipmentSlot.get(currIndex);
        return currSlot.orElseThrow(() -> new RolladieException(equipmentType + " is not equipped!"));
    }

    public int getEquipmentAttack() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getAttack())
                .reduce(0, Integer::sum);
    }

    public int getEquipmentHealth() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getHealth())
                .reduce(0, Integer::sum);
    }

    public int getEquipmentDefense() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getDefense())
                .reduce(0, Integer::sum);
    }

    public int getEquipmentAgility() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().getAgility())
                .reduce(0, Integer::sum);
    }

    public String toString() {
        return equipmentSlot.stream().filter(Optional::isPresent)
                .map(x -> x.get().toString())
                .reduce("", (x, y) -> x + "\n" + UI.LINE_SEPARATOR + "\n" + y);
    }

    /**
     * Returns the encoded string of equipmentSlot attributes to be saved
     *
     * @return encoded string
     */
    public String toText() {
        String equipmentsText = "";
        Optional<Equipment> armor = this.equipmentSlot.get(ARMOUR_SLOT);
        equipmentsText += armor.map(x -> x.toText() + SAVE_DELIMITER).orElse("armor" + SAVE_DELIMITER + "empty" + SAVE_DELIMITER);
        Optional<Equipment> boots = this.equipmentSlot.get(BOOTS_SLOT);
        equipmentsText += boots.map(x -> x.toText() + SAVE_DELIMITER).orElse("boots" + SAVE_DELIMITER + "empty" + SAVE_DELIMITER);
        Optional<Equipment> weapon = this.equipmentSlot.get(WEAPON_SLOT);
        equipmentsText += weapon.map(x -> x.toText()).orElse("weapon" + SAVE_DELIMITER + "empty");
        return equipmentsText;
    }
}
