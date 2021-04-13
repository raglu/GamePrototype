package com.company;

import java.util.ArrayList;

public abstract class Room {
    protected String name;
    protected ArrayList<Path> paths;
    protected ArrayList<Item> items;
    protected ArrayList<NPC> npcs;

    public Room(String name) {
        this.name = name;
        this.paths = new ArrayList<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    public abstract void setPaths();

    public NPC getNpc(String npcName) {
        for (NPC n : npcs) {
            if (n.getName().equalsIgnoreCase(npcName))
                return n;
        }
        return null;
    }

    public Path getPath(String direction) {
        for (Path p : paths) {
            if (p.getPathName().equalsIgnoreCase(direction))
                return p;
        }
        return null;
    }

    public Item getItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                items.remove(i);
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

    public String getStringOfItemName() {
        String stringOfItemNames = "";

        for (Item i : items) {
            stringOfItemNames += i.getName() + ", ";
        }
        return stringOfItemNames;
    }
}
