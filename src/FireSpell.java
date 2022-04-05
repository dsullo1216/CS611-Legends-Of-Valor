// Specialized version of Spell that includes specific debuffs
public class FireSpell extends Spell {

    public FireSpell(String name, int price, int minLevel, int damage, int mana) {
        super(name, price, minLevel, damage, mana, "defense");
        this.type = "Fire";
    }
    
}