package gameDSL;

public class Path {
    private String pathName;
    private Room destination;
    private Item requirement;

    public Path(Room destination, String pathName, Item requirement) {
        this.pathName = pathName;
        this.destination = destination;
        this.requirement = requirement;
    }

    public Path(Room destination, String pathName) {
        this.pathName = pathName;
        this.destination = destination;
        this.requirement = null;
    }

    public String getPathName() {
        return pathName;
    }

    public Room getDestination() {
        return destination;
    }

    public Item getRequirement() {
        return requirement;
    }
}
