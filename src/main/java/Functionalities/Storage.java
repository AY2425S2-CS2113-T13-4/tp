package Functionalities;

import Exceptions.RolladieException;
import Game.Characters.Character;
import Game.Characters.Enemy;
import Game.Characters.Player;
import Game.Events.Battle.Battle;
import Game.Events.Event;
import Game.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Storage {
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "savefile.txt";
    private static final String LOAD_DELIMITER = " \\| ";
    public static final String SAVE_DELIMITER = " | ";


    public static void saveGame(Player player, Event currentEvent, Queue<Event> eventsQueue) {
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(FILE_DIRECTORY, FILE_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);

            String playerText = player.toText();
            fw.write(playerText + System.lineSeparator());

            String currentEventText = currentEvent.toText();
            fw.write(currentEventText + System.lineSeparator());

            for (Event event: eventsQueue) {
                String textToAdd = event.toText();
                fw.write(textToAdd + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            UI.printMessage("savefile.txt failed to save");
        }
        UI.printMessage("savefile.txt saved successfully");
    }

    private static Character parseCharacterFromText(String characterType, String[] parameters) throws RolladieException {
        String healthBarsString = parameters[0];
        // https://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java
        String[] values = healthBarsString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
        int[]healthBars = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            try {
                healthBars[i] = Integer.parseInt(values[i]);
            } catch (NumberFormatException nfe) {
                UI.printMessage("Parsing failed");
            }
        }
        int attackValue = Integer.parseInt(parameters[1]);
        int defenseValue = Integer.parseInt(parameters[2]);
        String characterName = parameters[3];
        int maxHealth = Integer.parseInt(parameters[4]);
        if (characterType.equals("Player")) {
            return new Player(healthBars, attackValue, defenseValue, characterName);
        } else if (characterType.equals("Enemy")) {
            return new Enemy(healthBars, attackValue, defenseValue, characterName);
        } else {
            throw new RolladieException("Invalid Character Type");
        }
    }

    private static Event parseEventFromText(Player player, String[] parameters) throws RolladieException {
        Event temp = null;
        String eventType = parameters[0];
        switch(eventType) {
        case "Battle":
            try {
                Enemy enemy = (Enemy) parseCharacterFromText("Enemy", Arrays.copyOfRange(parameters, 1, parameters.length + 1));
                return new Battle(player, enemy);
            }
            catch (RolladieException e){
                System.out.println(e.getMessage());
        }
        default:
            throw new RolladieException("Invalid Event Type");
        }
    }

    public static Game loadGame() {
        File f = new File(FILE_DIRECTORY + FILE_NAME);
        Scanner s = null;
        try {
            s = new Scanner(f);
            // 1st line is player
            String[] firstLine = s.nextLine().split(LOAD_DELIMITER);
            Player player = (Player) parseCharacterFromText("Player", firstLine);

            // 2nd line is currentEvent
            String[] secondLine = s.nextLine().split(LOAD_DELIMITER);
            Event currentEvent = parseEventFromText(player, secondLine);

            // 3rd line onwards is eventsQueue
            Queue<Event> eventsQueue = new LinkedList<>();
            while (s.hasNext()) {
                String[] remainingLine = s.nextLine().split(LOAD_DELIMITER);
                Event event = parseEventFromText(player, remainingLine);
                eventsQueue.add(event);
            }
            UI.printMessage("savefile.txt loaded successfully");
            return new Game(player, currentEvent, eventsQueue);

        } catch (FileNotFoundException e) {
            UI.printMessage("savefile.txt not found!");
        } catch (RolladieException e) {
            UI.printMessage("savefile.txt corrupted");
        }
        return new Game();
    }
}
