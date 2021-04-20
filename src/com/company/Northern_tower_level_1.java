package com.company;

public class Northern_tower_level_1 extends Room {

    private static Northern_tower_level_1 instance = new Northern_tower_level_1();

    public static Northern_tower_level_1 getInstance() {
        return instance;
    }

    private Northern_tower_level_1() {
        super("Northern Tower level 1");

        setItems();
    }

    public void setPaths() {
        paths.add(new Path(Northern_tower_level_2.getInstance(),"stairs", new Key()));
        paths.add(new Path(Nowhere.getInstance(),"door"));
    }

    private void setItems(){
        items.add(new Sword());
        items.add(new Key());
    }

}
