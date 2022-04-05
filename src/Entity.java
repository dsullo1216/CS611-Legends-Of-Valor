// Abstract class to serve as a template for Heroes and Monsters to fight one another
public abstract class Entity {
    
    protected String type;
    protected String name;
    protected int level;
    protected int hp;

    public abstract String toString();

    public abstract String description();

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHP() {
        return hp;
    }

    public int updateHP(int newHP) {
        this.hp = newHP;
        return newHP;
    }

}