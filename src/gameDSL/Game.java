package gameDSL;

import java.util.ArrayList;

public class Game {

    private final String gameWorld = "Luigi's tower";
    private boolean playing = true;

    private final GameRules gameRules;
    ArrayList<Room> rooms;
    ArrayList<Player> players;
    ArrayList<NPC> npcs;

    public Game() {
        gameRules = new GameRules(this);

        rooms = EntityGenerator.generateRooms();
        players = EntityGenerator.generatePlayers();
        npcs = EntityGenerator.generateNPCs();

    }

    public void play() {

        System.out.println("Welcome to " + gameWorld);

        while (playing) {
            for (Player player : players) {
                printSituation(player);
                Command command = Parser.getCommand(player.getName());
                processPlayerCommand(player, command);
            }
            for (NPC npc : npcs) {
                processNPC(npc);
            }
            gameRules.checkRules();
        }
        System.out.println("Thank you for playing. Good bye");
    }

    private void printSituation(Player player) {
        System.out.println("\n" + player.getName() + " is now in " + player.getCurrentRoom().getName());
        if (player.isInCombat())
            System.out.println(player.getName() + " is in combat with " + player.getTargetHostileNPC().getName());
        System.out.println("- Exits in this room: " + player.getCurrentRoom().getExits());
        System.out.println("- Items in this room: " + player.getCurrentRoom().getItemNames());
        System.out.println("- NPCs in this room: " + player.getCurrentRoom().getNpcNames());
    }

    private void processPlayerCommand(Player player, Command command) {

        String firstWord = command.getFirstWord();
        String secondWord = command.getSecondWord();

        switch (firstWord) {
            case "quit" -> quit();
            case "help" -> printHelp();
            case "go" -> goPath(player, secondWord);
            case "inventory" -> printInventory(player);
            case "equip" -> equipItem(player, secondWord);
            case "take" -> takeItem(player, secondWord);
            case "drop" -> dropItem(player, secondWord);
            case "attack" -> attackNPC(player, secondWord);
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

    private void goPath(Player player, String pathName) {
        if (player.isInCombat()) {
            if (!player.getTargetHostileNPC().isEscapable()){
                System.out.println(player.getName() + " cannot escape " + player.getTargetHostileNPC().getName());
                return;
            }
        }

        if (pathName == null) {
            System.out.println("Go where?");
            return;
        }

        Path chosenPath = player.getCurrentRoom().findPath(pathName);

        if (chosenPath == null) {
            System.out.println(player.getName() + " can't go that way");
            return;
        }

        Item requirement = chosenPath.getRequirement();
        if (requirement != null) {
            if (!player.hasItem(requirement.getName())) {
                System.out.println(player.getName() + " need a " + requirement.getName() + " to enter");
                return;
            }
        }

        player.setInCombat(false);
        player.setTargetHostileNPC(null);

        player.setCurrentRoom(chosenPath.getDestination());
        System.out.println(player.getName() + " went through " + chosenPath.getPathName());
    }

    private void printInventory(Player player) {
        ArrayList<Item> inventory = player.getInventory();
        System.out.print("Inventory: ");
        for (Item i : inventory) {
            System.out.print(i.getName() + ", ");
        }
        Item equipped = player.getEquipped();
        System.out.print("\nEquipped: ");
        if (equipped != null)
            System.out.print(equipped.getName());
        System.out.println();
    }

    private void equipItem(Player player, String itemName) {
        if (itemName == null) {
            System.out.println("Equip what?");
            return;
        }

        Item item = player.findItem(itemName);

        if (item == null) {
            System.out.println(player.getName() + " does not have that item");
            return;
        }
        if (!(item instanceof Weapon)) {
            System.out.println(player.getName() + " can't equip that item");
            return;
        }
        player.setEquipped(item);
        System.out.println(player.getName() + " equipped " + item.getName());
    }

    private void takeItem(Player player, String itemName) {
        if (itemName == null) {
            System.out.println("Take what?");
            return;
        }

        Item item = player.getCurrentRoom().findItem(itemName);

        if (item == null) {
            System.out.println(player.getName() + " can't take that");
            return;
        }

        int currentCarryWeight = 0;

        for (Item inventoryItem : player.getInventory()) {
            currentCarryWeight += inventoryItem.getWeight();
        }

        if (player.getCarryCapacity() < currentCarryWeight + item.getWeight()) {
            System.out.println(player.getName() + " cannot carry anymore items");
            return;
        }

        player.getCurrentRoom().removeItem(item);
        player.addItem(item);
        System.out.println(player.getName() + " took " + item.getName());

    }

    private void dropItem(Player player, String itemName) {
        if (itemName == null) {
            System.out.println("Drop what?");
            return;
        }

        Item item = player.findItem(itemName);

        if (item == null) {
            System.out.println(player.getName() + " does not have that item");
            return;
        }

        player.getCurrentRoom().addItem(item);
        player.removeItem(item);
        System.out.println(player.getName() + " dropped " + item.getName());
    }

    private void attackNPC(Player player, String npcName) {
        Weapon equippedWeapon = (Weapon) player.getEquipped();
        if (equippedWeapon == null) {
            System.out.println(player.getName() + " can't attack without a weapon equipped");
            return;
        }

        if (npcName == null) {
            System.out.println("Attack what?");
            return;
        }

        NPC targetNPC = player.currentRoom.findNPC(npcName);

        if (targetNPC == null) {
            System.out.println("That NPC is not here");
            return;
        }

        if (!(targetNPC instanceof HostileNPC)) {
            System.out.println("Non-hostile NPCs cannot be attacked");
            return;
        }

        HostileNPC targetHostileNPC = (HostileNPC) targetNPC;
        targetHostileNPC.reduceHealth(equippedWeapon.getDamage());
        equippedWeapon.reduceDurability(1);

        player.setInCombat(true);
        player.setTargetHostileNPC(targetHostileNPC);
        targetHostileNPC.setInCombat(true);
        targetHostileNPC.setTargetPlayer(player);

        System.out.println(player.getName() + " attacked " + targetHostileNPC.getName());
        System.out.println(targetHostileNPC.getName() + " has " + targetHostileNPC.getHealth() + " health");
        if (equippedWeapon.getDurability() ==0)
            System.out.println(player.getName() + "equipped weapon broke");
    }

    private void processNPC(NPC npc) {
        if (npc instanceof HostileNPC) {
            processHostileNPC((HostileNPC) npc);
        }
        //TODO wtf do none-hostile NPCs do?
    }

    private void processHostileNPC(HostileNPC hostileNPC) {
        if(hostileNPC.getHealth() <=  0){
            System.out.println(hostileNPC.getName() + " died");
            npcs.remove(hostileNPC);
            hostileNPC.getCurrentRoom().removeNPC(hostileNPC);
        }

        if (hostileNPC.isInCombat()) {
            hostileNPC.attackPlayer();
            Player targetPlayer = hostileNPC.getTargetPlayer();
            System.out.println(hostileNPC.getName() + " attacked " + targetPlayer.getName());
            System.out.println(targetPlayer.getName() + " has " + targetPlayer.getHealth() + " health");
            return;
        }

        if (hostileNPC.isAggressive() && hostileNPC.getCurrentRoom().hasPlayers()) {
            Player targetPlayer = hostileNPC.getCurrentRoom().getRandomPlayer();
            targetPlayer.setInCombat(true);
            targetPlayer.setTargetHostileNPC(hostileNPC);
            hostileNPC.setInCombat(true);
            hostileNPC.setTargetPlayer(targetPlayer);

            System.out.println(hostileNPC.getName() + " has engaged in combat with " + targetPlayer.getName());
        }
    }

    public void gameOver() {
        playing = false;
        System.out.println("GameOver");
    }

    public void winGame() {
        System.out.println("You won the game!");
    }
}
