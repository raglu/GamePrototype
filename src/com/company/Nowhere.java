package com.company;

public class Nowhere extends Room {
    static private Nowhere instance = new Nowhere();

    public static Nowhere getInstance() {
        return instance;
    }

    private Nowhere() {
        super("Nowhere");

        setItems();
    }

    public void setPaths(){
        paths.add(new Path(Northern_tower_level_1.getInstance(),"door"));
    }

    private void setItems(){
        items.add(new Glass_sword());
    }
}
