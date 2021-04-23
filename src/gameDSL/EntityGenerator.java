package gameDSL;

import java.util.ArrayList;

public class EntityGenerator {

    public static ArrayList<Room> generateRooms() {
        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(Northern_tower_level_1.getInstance());
        rooms.add(Northern_tower_level_2.getInstance());
        rooms.add(Nowhere.getInstance());

        for (Room room : rooms) {
            room.setPaths();
            room.setItems();
        }

        return rooms;
    }

    public static ArrayList<Player> generatePlayers() {
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Luigi(Northern_tower_level_1.getInstance()));
        players.add(new Mario(Northern_tower_level_1.getInstance()));

        return players;
    }

    public static ArrayList<NPC> generateNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Skaven(Northern_tower_level_1.getInstance()));
        npcs.add(new Monster(Northern_tower_level_1.getInstance()));

        return npcs;
    }

}
