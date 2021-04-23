package gameDSL;

public abstract class HostileNPC extends NPC {
    protected boolean escapable;
    protected int health;
    protected int damage;
    private boolean inCombat = false;
    private Player targetPlayer = null;
    private boolean aggressive;

    public HostileNPC(Room currentRoom, String name, boolean escapable, int health, int damage) {
        super(currentRoom, name);
        this.escapable = escapable;
        this.health = health;
        this.damage = damage;
        this.aggressive = false;
    }

    public HostileNPC(Room currentRoom, String name, boolean escapable, int health, int damage, boolean aggressive) {
        super(currentRoom, name);
        this.escapable = escapable;
        this.health = health;
        this.damage = damage;
        this.aggressive = aggressive;
    }

    public boolean isEscapable() {
        return escapable;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int damage) {
        health -= damage;
    }

    public void attackPlayer() {
        targetPlayer.reduceHealth(damage);
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public boolean isInCombat() {
        return inCombat;
    }

    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
    }
}
