package Events;

import Characters.Players.Player;
import Equipment.Equipment;
import exceptions.RolladieException;
import Functions.UI;
import UI.ShopUI;
import UI.Narrator;

import java.io.Serializable;

import static Functions.UI.readIntegerInput;


public class Shop extends Event {
    public Equipment[] equipments;
    private boolean isDone;

    public Shop(Player player, Equipment[] equipments) {
        super(player);
        this.equipments = equipments;
        isDone = false;
    }

    @Override
    public void run() throws RolladieException, InterruptedException {
        Narrator.commentOnShopEntry();
        startShopping();
        Narrator.commentOnShopExit();
    }


    public void handleShopInput(int input) throws RolladieException, InterruptedException {
        switch (input) {
        case 1: //buy
            ShopUI.printBuyInstructions();
            int buyInput = readIntegerInput();
            if (buyInput >= equipments.length || buyInput < 0) {
                UI.printErrorMessage("Buy index out of range!");
                break;
            }
            handleBuyInput(buyInput);
            break;
        case 2: //sell
            ShopUI.printSellInstructions();
            int sellInput = readIntegerInput();
            if (sellInput >= 3 || sellInput < 0) {
                UI.printErrorMessage("Sell index out of range!");
                break;
            }
            handleSellInput(sellInput);
            break;
        case 3: //sell
            isDone = true;
            break;
        default:
            throw new RolladieException("You can only use \"buy\", \"sell\" or \"leave\" bro");
        }
    }

    public void handleBuyInput(int buyInput) throws RolladieException, InterruptedException {
        if (buyInput > equipments.length || buyInput < 1) {
            throw new RolladieException("Buy index out of range!");
        }
        Equipment equipment = equipments[buyInput - 1];
        boolean hasBought = player.buyEquipment(equipment);
        if (hasBought) {
            Narrator.commentOnShopBuy(player, equipment);
        } else {
            UI.printErrorMessage("Not enough gold!");
        }
    }

    public void handleSellInput(int sellInput) throws RolladieException, InterruptedException {
        if (sellInput >= 3 || sellInput < 0) {
            throw new RolladieException("Buy index out of range!");
        }
        Equipment equipment = player.getEquipment(sellInput);

        if (equipment.getId() != -1) {
            player.sellEquipment(sellInput);
            Narrator.commentOnShopSell(player, equipment);
        } else {
            UI.printErrorMessage("No Equipment at this slot!");
        }
    }


    public void startShopping() throws RolladieException, InterruptedException {
        while (!isDone) {
            ShopUI.printShopCollection(equipments);
            ShopUI.printShopMenu(player);
            int input = readIntegerInput();
            handleShopInput(input);
        }
    }
}
