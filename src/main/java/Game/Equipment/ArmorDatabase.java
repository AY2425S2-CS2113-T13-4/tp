package Game.Equipment;


import Exceptions.RolladieException;

import java.util.ArrayList;

public class ArmorDatabase {
    private static ArrayList<Armor> armorList = new ArrayList<>();

    // Static block to initialize predefined armors
    static {
        armorList.add(new Armor("Iron Armor", 50, 100, 5));
        armorList.add(new Armor("Steel Armor", 70, 150, 3));
        armorList.add(new Armor("Leather Armor", 20, 50, 10));
        armorList.add(new Armor("Dragon Scale Armor", 150, 200, 20));
    }

    // Get all armor
    public static ArrayList<Armor> getAllArmor() {
        return armorList;
    }

    /**
     * Find armor based on armorName
     * @param name Name of the armor being queried
     * @return Corresponding armor
     */
    public static Armor getArmorByName(String name) throws RolladieException {
        for (Armor armor : armorList) {
            if (armor.getArmorName().equalsIgnoreCase(name)) {
                return armor;
            }
        }
        throw new RolladieException("Armor not found!");
    }
}
