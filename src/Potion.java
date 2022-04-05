// Subclass of Item to represent Potion items and their attributes. Includes all necessary methods
public class Potion extends Item {

    public static final String DESCRIPTION = "Potion Name / Price / Level Required / Buff Amount / Stat Buffed";
    private int buffAmt;
    private String buffedStat;

    public Potion(String name, int price, int minLevel, int buffAmt, String buffedStat) {
        this.name = name;
        this.price = price;
        this.minLevel = minLevel;
        this.buffAmt = buffAmt;
        this.buffedStat = buffedStat;
        this.type = "Potion";
    }

    public String toString() {
        return name + " / " + Integer.toString(price) + " / " + Integer.toString(minLevel) + " / " + Integer.toString(buffAmt) + " / " + buffedStat;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof Potion)) {
            return false;
        }
        
        Potion otherP = (Potion) other;
        return (this.name == otherP.getName());
    
    }

    public int getBuffAmt() {
        return buffAmt;
    }

    public String getBuffedStat() {
        return buffedStat;
    }

    public String description() {
        return DESCRIPTION;
    }

}