package gameDSL;

public abstract class Weapon extends Item {

    protected int damage;
    protected int durability;

    public Weapon(String name, int weight, int damage, int durability) {
        super(name, weight);
        this.damage = damage;
        this.durability = durability;
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public void reduceDurability(int i){
        durability =- i;
    }
}
