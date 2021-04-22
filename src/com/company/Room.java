package com.company;

import java.util.ArrayList;
import java.util.Random;

public abstract class Room {
    protected String name;
    protected ArrayList<Path> paths;
    protected ArrayList<Item> items;
    protected ArrayList<NPC> npcs;
    protected ArrayList<Player> players;

    public Room(String name) {
        this.name = name;
        this.paths = new ArrayList<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public abstract void setPaths();

    public abstract void setItems();

    public NPC findNPC(String npcName) {
        for (NPC n : npcs) {
            if (n.getName().equalsIgnoreCase(npcName))
                return n;
        }
        return null;
    }

    public Path findPath(String direction) {
        for (Path p : paths) {
            if (p.getPathName().equalsIgnoreCase(direction))
                return p;
        }
        return null;
    }

    public Item findItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public String getItemNames() {
        String itemNames = "";
        for (Item item : items) {
            itemNames += item.getName() + "  ";
        }
        return itemNames;
    }

    public String getExits() {
        String exits = "";
        for (Path path : paths) {
            exits += path.getPathName() + "  ";
        }
        return exits;
    }

    public String getNpcNames() {
        String npcNames = "";
        for (NPC npc : npcs) {
            npcNames += npc.getName() + "  ";
        }
        return npcNames;
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    public boolean hasPlayers(){
        return players.size() > 0;
    }

    public Player getRandomPlayer() {
        Random random = new Random();
        int index = random.nextInt(players.size());
        return players.get(index);
    }
}
