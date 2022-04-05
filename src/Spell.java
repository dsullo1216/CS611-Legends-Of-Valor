// Subclass of Weapon that implements additional features needed for Spell to work.
public class Spell extends Weapon {

    public static final String DESCRIPTION = "Spell Name / Price / Level Required / Mana Required / Stat Reduced";
    protected int mana;
    protected String reducedStat;

    public Spell(String name, int price, int minLevel, int damage, int mana, String reducedStat) {
        super(name, price, minLevel, damage);
        this.mana = mana;
        this.reducedStat = reducedStat;
    }

    @Override
    public String toString() {
        return name + " / " + Integer.toString(price) + " / " + Integer.toString(minLevel) + " / " + Integer.toString(damage) + " / " +Integer.toString(mana) + " / " + reducedStat;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof Spell)) {
            return false;
        }
        
        Spell otherS = (Spell) other;
        return (this.name == otherS.getName());
    
    }

    public int getMana() {
        return mana;
    }

    public String getReducedStat() {
        return reducedStat;
    }
    
    @Override
    public String description() {
        return DESCRIPTION;
    }

}