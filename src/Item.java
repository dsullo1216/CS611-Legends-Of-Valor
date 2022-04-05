// Abstract class representing Items for implementing Heroes and Monsters.
public abstract class Item {

    protected String name;
    protected int price;
    protected int minLevel;
    protected String type;

    public abstract String toString();

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public String getType() {
        return type;
    }

    public abstract String description();
    
}