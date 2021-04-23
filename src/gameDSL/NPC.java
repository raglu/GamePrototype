package gameDSL;

public abstract class NPC {

    private String name;
    private Room currentRoom;

    public NPC(Room currentRoom, String name) {
        this.name = name;
        this.currentRoom = currentRoom;

        currentRoom.addNPC(this);
    }

    public String getName() {
        return name;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom.removeNPC(this);
        currentRoom = newRoom;
        currentRoom.addNPC(this);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
