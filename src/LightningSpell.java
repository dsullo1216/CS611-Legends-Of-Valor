// Specialized version of Spell that includes specific debuffs
public class LightningSpell extends Spell {
    
    public LightningSpell(String name, int price, int minLevel, int damage, int mana) {
        super(name, price, minLevel, damage, mana, "dodge_chance");
        this.type = "Lightning";
    }

}