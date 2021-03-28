package com.company;

import java.util.ArrayList;

public class RoomMaker {

    public static Room createRooms(ArrayList<Item> inventory) {
       return new Room("asdf");
    }

    private static void link(Room r1, String dir1, Room r2, String dir2) {
        r1.setExit(dir1, r2);
        r2.setExit(dir2, r1);
    }


}
