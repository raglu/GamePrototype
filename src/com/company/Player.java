package com.company;

import java.util.ArrayList;

public class Player {

    protected String name;
    protected int health;
    protected int carryCapacity;
    protected ArrayList<Item> inventory;
    protected Item equipped;
    protected Room currentRoom;

    private boolean inCombat = false;

    private HostileNPC targetHostileNPC = null;

    public Player(Room currentRoom, String name, int health, int carryCapacity) {
        this.currentRoom = currentRoom;
        this.name = name;
        this.health = health;
        this.carryCapacity = carryCapacity;
        this.inventory = new ArrayList<>();

        currentRoom.addPlayer(this);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom.removePlayer(this);
        currentRoom = newRoom;
        currentRoom.addPlayer(this);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getCarryCapacity() {
        return carryCapacity;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Item getEquipped() {
        return equipped;
    }

    public void setEquipped(Item item) {
        equipped = item;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public Item findItem(String itemName) {
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName))
                return i;
        }
        return null;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
        if (equipped == item)
            equipped = null;
    }

    public boolean hasItem(String itemName) {
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName))
                return true;
        }
        return false;
    }

    public void reduceHealth(int damage) {
        health -= damage;
    }

    public boolean isInCombat() {
        return inCombat;
    }

    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    public HostileNPC getTargetHostileNPC() {
        return targetHostileNPC;
    }

    public void setTargetHostileNPC(HostileNPC targetHostileNPC) {
        this.targetHostileNPC = targetHostileNPC;
    }
}
