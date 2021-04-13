package com.company;

import java.util.ArrayList;

public class Player {

    protected String name;
    protected int health;
    protected int carryCapacity;
    protected ArrayList<Item> inventory;
    protected Item equipped;

    public Player(String name, int health, int carryCapacity) {
        this.name = name;
        this.health = health;
        this.carryCapacity = carryCapacity;
        this.inventory = new ArrayList<>();
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

    public Item getItem(String itemName) {
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName))
                return i;
        }
        return null;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public boolean hasItem(String itemName){
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName))
                return true;
        }
        return false;
    }
}
