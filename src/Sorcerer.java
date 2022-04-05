import java.io.IOException;

// Specialized type of Hero that includes specialized version of level up
public class Sorcerer extends Hero {
    
    public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int wallet, int level) throws IOException {
        super(name, mana, strength, agility, dexterity, wallet, level);
        this.type = "Sorcerer";
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.dexterity *= 1.05;
        this.agility *= 1.05;
    }

}