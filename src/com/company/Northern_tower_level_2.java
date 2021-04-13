package com.company;

public class Northern_tower_level_2 extends Room {

    static private Northern_tower_level_2 instance = new Northern_tower_level_2();

    public static Northern_tower_level_2 getInstance() {
        return instance;
    }

    private Northern_tower_level_2() {
        super("Northern Tower level 2");
    }

    public void setPaths() {
        paths.add(new Path(Northern_tower_level_1.getInstance(),"stairs"));
    }

}
