package com.company;

import java.util.ArrayList;

public class Game {
    private final Parser parser;
    private Room currentRoom;
    private final ArrayList<Item> inventory;


    public Game() {
        parser = new Parser();
        inventory = new ArrayList<>();
        currentRoom = RoomMaker.createRooms(inventory);
    }

    public void play() {

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private boolean processCommand(Command command) {
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        switch (commandWord) {
            case "quit":
                return true;
            case "help":
                printHelp();
                break;
            case "inventory":
                printInventory();
                break;
            case "look":
                printRoomDescription();
                break;
            default:
                getRoomResponse(command);
                break;
        }

        return false;
    }

    private void printInventory() {
        if (inventory.size() == 0) {
            System.out.println("you are not carrying anything");
        } else {
            System.out.print("You have the following:");
            for (Item item : inventory) {
                System.out.print(" " + item.getDesc());
            }
            System.out.println();
            System.out.println("Total weight: " + Item.totalWeight(inventory));
        }
    }

    private void printHelp() {

    }

    private void printRoomDescription() {
        System.out.println(currentRoom.getLongDescription());
    }

    private void getRoomResponse(Command command) {
        Room r = currentRoom.respond(command, inventory);
        if (r != null) currentRoom = r;
    }

}
