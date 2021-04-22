package com.company;

public class Monster extends HostileNPC {

    public Monster(Room currentRoom) {
        super(currentRoom, "Monster", false, 4, 2);
    }
}
