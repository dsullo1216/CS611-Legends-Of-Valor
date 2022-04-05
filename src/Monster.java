// Subclass of Entity that represents a mob for the hero to fight against. Implements all necessary methods.
public class Monster extends Entity {
    
    public static final String DESCRIPTION = "Type    /   Name    /   Level    /   HP    /   Damage    /   Defense    /   Dodge Chance";
    protected int damage;
    protected int defense;
    protected int dodgeChance;

    public Monster(String name, int level, int damage, int defense, int dodgeChance) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
        this.hp = level * 100;
    }

    public String toString() {
        return type + " / " + 
               name + " / " + 
               Integer.toString(level) + " / " + 
               Integer.toString(hp) + " / " + 
               Integer.toString(damage) + " / " + 
               Integer.toString(defense) + " / " +
               Integer.toString(dodgeChance);
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof Monster)) {
            return false;
        }
        
        Monster otherM = (Monster) other;
        return (this.name == otherM.getName());

    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int updateDamage(int newDamage) {
        this.damage = newDamage;
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int updateDefense(int newDefense) {
        this.defense = newDefense;
        return defense;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public int updateDodgeChance(int newDodgeChance) {
        this.dodgeChance = newDodgeChance;
        return dodgeChance;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

}