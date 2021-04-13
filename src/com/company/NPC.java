package com.company;

public abstract class NPC {
    protected String name;
    protected boolean escapable;
    protected  int health;
    protected  int damage;

    public NPC(String name, boolean escapable, int health, int damage) {
        this.name = name;
        this.escapable = escapable;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }
}
