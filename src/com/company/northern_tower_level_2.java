package com.company;

import java.util.ArrayList;

public class northern_tower_level_2 extends Room{
    private String name = "Northern Tower level 2";
    private ArrayList<Path> paths;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     *
     * @param description
     */
    public northern_tower_level_2(String description) {
        super(description);
    }
}
