package com.company;

import java.util.ArrayList;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 * <p>
 * pld notes:
 * <p>
 * In this version of pld's inheritance strategy, Room.response() supports
 * the GO command, too, and returns a new Room if applicable.
 * It returns null if no change in room is anticipated.
 * <p>
 * inventory is a parameter to respond(). If other methods (eg getDescription)
 * need inventory, we use the "constructor trick".
 */

public class Game {
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Item> inventory;

//  public Room valveroom;

//    private static Game theGame = null;


//    boolean serpent_happy = false;


    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Create the game and initialise its internal map.
     */

    public Game() {
        inventory = new ArrayList<Item>();
        currentRoom = RoomMaker.createRooms(inventory);
        parser = new Parser();
        //theGame = this;
    }


    /* */

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        //currentRoom.onEntry();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) {
        //boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        if (commandWord.equals("quit")) {       // serpent
            return true;
        }

        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("inventory")) {
            printInventory();
        } else if (commandWord.equals("look")) {
            System.out.println(currentRoom.getLongDescription());
        } else {
            Room r = currentRoom.respond(command, inventory);
            if (r != null) currentRoom = r;
        }

        return false;      // always false
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }


    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else
            return true;  // signal that we want to quit
    }

    /**
     * printInventory prints the contents of "inventory"
     */
    private void printInventory() {
        if (inventory.size() == 0) {
            System.out.println("you are not carrying anything");
        } else {
            System.out.print("You have the following:");
            for (int n = 0; n < inventory.size(); n++) {
                Item item = (Item) inventory.get(n);
                System.out.print(" " + item.getDesc());
            }
            System.out.println();
            System.out.println("Total weight: " + Item.totalWeight(inventory));
        }
    }

    /* */
    private void link(Room r1, String dir1, Room r2, String dir2) {
        r1.setExit(dir1, r2);
        r2.setExit(dir2, r1);
    }

    public static void main(String[] argv) {
        Game g = new Game();
        g.play();
    }
    /* */
}
