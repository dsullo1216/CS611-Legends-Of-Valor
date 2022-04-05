// Subclass of the Item class that represents armor that can be worn by the hero.
public class Armor extends Item {

    public static final String DESCRIPTION = "Armor Name / Price / Level Required / Defense";
    private int defense;
    
    public Armor(String name, int price, int minLevel, int defense) {
        this.name = name;
        this.price = price;
        this.minLevel = minLevel;
        this.defense = defense;
        this.type = "Armor";
    }

    public String toString() {
        return name + " / " + Integer.toString(price) + " / " + Integer.toString(minLevel) + " / " + Integer.toString(defense);
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof Armor)) {
            return false;
        }
        
        Armor otherA = (Armor) other;
        return (this.name == otherA.getName());
    
    }

    public int getDefense() {
        return defense;
    }

    public String description() {
        return DESCRIPTION;
    }

}