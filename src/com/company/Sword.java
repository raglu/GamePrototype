package com.company;

public class Sword extends Item {

    private boolean obtainable = true;
    private int weight = 1;
    private int damage = 3;
    private int durability = 5;

    public Sword(String d) {
        super(d);
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public void useOnce() {
        durability--;
    }

}
