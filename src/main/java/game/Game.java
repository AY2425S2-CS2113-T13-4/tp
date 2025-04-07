package game;

import events.Loot;
import events.Shop;
import exceptions.RolladieException;
import functions.Storage;
import functions.UI.UI;
import players.Player;
import equipments.armors.ArmorDatabase;
import equipments.boots.BootsDatabase;
import equipments.Equipment;
import equipments.weapons.WeaponDatabase;
import events.Battle;
import events.Event;

import java.io.Serializable;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Manages all game logic specifically: Event Generation and Sequence
 */
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_NUMBER_OF_WAVES = 8;
    private Queue<Event> eventsQueue = new LinkedList<>();
    private Player player;
    private Event currentEvent;
    private int wave;
    private int turnsWithoutShop = 0;
    private boolean hasWonCurrBattle = false;

    /**
     * Constructor to instantiate a new game
     * Creates a new player based on a Hero preset
     * Generates the event queue
     * Polls the first event from the queue to be the current event
     */
    public Game() {
        this.player = Player.createNewPlayer();
        this.wave = 1;
        this.eventsQueue = generateEventQueue();
        this.currentEvent = nextEvent();
    }

    public int getWave() {
        return this.wave;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Overloaded constructor used to generate defined game
     * Main usage is within the Storage class to load game from save file
     *
     * @param player
     * @param currentEvent
     * @param eventsQueue
     */
    public Game(Player player, Event currentEvent, Queue<Event> eventsQueue) {
        this.player = player;
        this.eventsQueue = eventsQueue;
        this.currentEvent = currentEvent;
    }

    /**
     * Runs the current game until the event sequence is completed
     * Ends the game prematurely if the player died within the event
     */
    public void run() {
        while (this.currentEvent != null && this.player.isAlive()) {
            try {
                //If current battle is won, sets loot event to give rewards
                currentEvent.setHasWon(hasWonCurrBattle);
                //Saves game on loot or shop screen after a battle.
                if (this.currentEvent instanceof Loot) {
                    saveGame();
                }
                this.currentEvent.run();
                if (this.currentEvent.isExit) {
                    UI.printExitMessage();
                    return;
                }
                if (!player.isAlive()) {
                    break;
                }
                //Checks if current battle is won
                if (this.currentEvent instanceof Battle) {
                    this.wave++;
                    hasWonCurrBattle = currentEvent.getHasWon();
                }
                this.currentEvent = nextEvent();
            } catch (RolladieException | InterruptedException e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
        if (!this.player.isAlive()) {
            UI.printDeathMessage();
        } else {
            UI.printWinMessage();
        }
    }



    /**
     * Returns a filled queue of events
     * Used during the construction of a new game
     *
     * @return eventsQueue
     */
    private Queue<Event> generateEventQueue() {
        Queue<Event> eventsQueue = new LinkedList<>();
        int i;
        for (i = 1; i <= MAX_NUMBER_OF_WAVES; i++) {
            eventsQueue.add(generateBattle(i));
            eventsQueue.add(generateLoot((i + 1) * 10));
            if(i % 2 == 0) {
                eventsQueue.add(generateShopEvent(i));
            }
        }
        eventsQueue.add(generateBattle(i+1));
        return eventsQueue;
    }

    /**
     * Returns a battle to insert into the event queue.
     *
     * @return Event
     */
    private Battle generateBattle(int wave) {
        Battle newBattle = new Battle(this.player, wave);
        return newBattle;
    }

    private Event generateLoot(int loot) {
        return new Loot(this.player, loot);
    }

    public Event generateShopEvent(int wave) {
        Equipment[] equipmentsForSale = {
                ArmorDatabase.getArmorByIndex((wave / 2) - 1),
                BootsDatabase.getBootsByIndex((wave / 2) - 1),
                WeaponDatabase.getWeaponByIndex((wave / 2) - 1),
        };
        return new Shop(this.player, equipmentsForSale);
    }

    /**
     * returns the next event inside the event queue
     *
     * @return Event
     */
    private Event nextEvent() {
        return this.eventsQueue.poll();
    }

    /**
     * Calls the Storage class to save the current game status
     */
    private void saveGame() {
        UI.printMessage("💾 Save game? (y/n): ");
        String saveInput = UI.readInput();
        if (saveInput.equalsIgnoreCase("y")) {
            int saveSlot = Integer.parseInt(UI.promptSaveFile());
            Storage.saveGame(saveSlot, this);
        }
    }

}
