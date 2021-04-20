package com.company;

public abstract class NPC {

    private String name;
    private Room currentRoom;

    public NPC(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }
}
