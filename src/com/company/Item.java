package com.company;

public abstract class Item {

    protected String name;
    protected int weight;

    public Item(String name) {
        this.name = name;
        this.weight = 0;
    }

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}


