package com.company;

public abstract class HostileNPC extends NPC {
    protected boolean escapable;
    protected int health;
    protected int damage;

    public HostileNPC(String name, Room currentRoom, boolean escapable, int health, int damage) {
        super(name, currentRoom);
        this.escapable = escapable;
        this.health = health;
        this.damage = damage;
    }

    public boolean isEscapable() {
        return escapable;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void reduceHealth(int damage){
        health =- damage;
    }
}
