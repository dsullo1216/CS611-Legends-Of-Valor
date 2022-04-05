// Specialized version of Spell that includes specific debuffs
public class IceSpell extends Spell {

    public IceSpell(String name, int price, int minLevel, int damage, int mana) {
        super(name, price, minLevel, damage, mana, "damage");
        this.type = "Ice";
    }
    
}