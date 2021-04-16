package com.company;

import java.util.ArrayList;

public class Game {

    public boolean playing = true;

    private final Parser parser;
    private final GameRules gameRules;

    public Room currentRoom;
    public Player luigi;

    private Room asdf; //paths are a bit funky because this stupid implementation causes circular dependency
    private Room fdas;
    private String gameWorld = "GameWorldName";

    public Game() {
        parser = new Parser();
        gameRules = new GameRules(this);
        currentRoom = Northern_tower_level_1.getInstance();
        luigi = new Luigi();

        asdf = Nowhere.getInstance();
        fdas = Northern_tower_level_2.getInstance();
        currentRoom.setPaths();
        asdf.setPaths();
        fdas.setPaths();
    }

    public void play() {

        System.out.println("Welcome to " + gameWorld);
        System.out.println("You are now in " + currentRoom.getName());
        System.out.println("Items in this room: " + currentRoom.getStringOfItemName());

        while (playing) {
            Command command = parser.getCommand();
            processCommand(command);
            gameRules.checkRules();
        }
        System.out.println("Thank you for playing. Good bye");
    }

    private void processCommand(Command command) {

        String firstWord = command.getFirstWord();
        String secondWord = command.getSecondWord();

        switch (firstWord) {
            case "quit" -> quit();
            case "help" -> printHelp();
            case "go" -> goPath(secondWord);
            case "inventory" -> printInventory();
            case "equip" -> equipItem(secondWord);
            case "take" -> takeItem(secondWord);
            case "drop" -> dropItem(secondWord);
            case "attack" -> attackNPC(secondWord);
            default -> System.out.println("I don't know what you mean...");
        }
    }

    private void quit() {
        System.out.println("Quitting game...");
        playing = false;
    }

    private void printHelp() {
        System.out.println("Possible commands:");
        System.out.println("quit  help  go  inventory  equip  take  drop  attack");
    }

    private void goPath(String pathName) {
        if (pathName == null) {
            System.out.println("Go where?");
            return;
        }

        Path chosenPath = currentRoom.getPath(pathName);

        if (chosenPath == null) {
            System.out.println("You can't go that way");
        } else {

            Item requirement = chosenPath.getRequirement();
            if (requirement != null)
                if (!luigi.hasItem(requirement.getName())) {
                    System.out.println("You need a " + requirement.getName() + " to enter");
                    return;
                }

            currentRoom = chosenPath.getDestination();
            System.out.println("You are now in " + currentRoom.getName());
            System.out.println("Items in this room: " + currentRoom.getStringOfItemName());
        }
    }

    private void printInventory() {
        ArrayList<Item> inventory = luigi.getInventory();
        System.out.print("Inventory: ");
        for (Item i : inventory) {
            System.out.print(i.getName() + ", ");
        }
        Item equipped = luigi.getEquipped();
        if (equipped != null)
            System.out.println("\nCurrently equipped: " + equipped.getName());
        else
            System.out.println("\nYou don't have any items equipped");
    }

    private void equipItem(String itemName) {
        if (itemName == null) {
            System.out.println("Equip what?");
            return;
        }

        Item item = luigi.getItem(itemName);

        if (item == null) {
            System.out.println("You don't have that item");
        } else if (item instanceof Weapon) {
            luigi.setEquipped(item);
            System.out.println("You equipped " + item.getName());
        } else {
            System.out.println("You can't equip that item");
        }
    }

    private void takeItem(String itemName) {
        if (itemName == null) {
            System.out.println("Take what?");
            return;
        }

        Item item = currentRoom.getItem(itemName);

        if (item == null) {
            System.out.println("You can't take that");
        } else {
            luigi.addItem(item);
            System.out.println("You took " + item.getName());
        }
    }

    private void dropItem(String itemName) {
        if (itemName == null) {
            System.out.println("Drop what?");
            return;
        }

        Item item = luigi.getItem(itemName);

        if (item == null) {
            System.out.println("You don't have that item");
        } else {
            currentRoom.addItem(item);
            luigi.removeItem(item);
            System.out.println("You dropped " + item.getName());
        }

    }

    private void attackNPC(String npcName) {
        Weapon weapon = (Weapon) luigi.getEquipped();
        if(weapon == null){
            System.out.println("You can't attack, you do not have an weapon equipped");
            return;
        }

        if (npcName == null) {
            System.out.println("Attack what?");
            return;
        }

        NPC npc = currentRoom.getNpc(npcName);
        if(npc == null){
            System.out.println("That npc is not here");
            return;
        }



        weapon.useOnce();
    }

    public void gameOver() {
        playing = false;
        System.out.println("GameOver");
    }

}
