package com.company;

import java.util.ArrayList;

public class northern_tower_level_1{
    private final String name = "Northern Tower level 1";
    private ArrayList<Path> paths;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;

    public northern_tower_level_1() {
        this.paths = new ArrayList<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();

        setPaths();
        setItems();
        setEnemies();
    }

    private void setPaths() {
        paths.add(new Path());
        paths.add(new Path());
    }

    private void setItems(){
        items.add(new Sword(""));
        items.add(new Key(""));
    }

    private void setEnemies(){
        enemies.add(new Skaven());
        enemies.add(new Monster());
    }

}
