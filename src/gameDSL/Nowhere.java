package gameDSL;

public class Nowhere extends Room {
    static private Nowhere instance = new Nowhere();

    public static Nowhere getInstance() {
        return instance;
    }

    private Nowhere() {
        super("Nowhere");
    }

    public void setPaths(){
        paths.add(new Path(Northern_tower_level_1.getInstance(),"door"));
    }

    public void setItems(){
        items.add(new Glass_sword());
    }
}
