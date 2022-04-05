import java.io.IOException;

// Subclass of Hero to represent Warrior class. Implements specialized version of level_up
public class Warrior extends Hero {
    
    public Warrior(String name, int mana, int strength, int agility, int dexterity, int wallet, int level) throws IOException {
        super(name, mana, strength, agility, dexterity, wallet, level);
        this.type = "Warrior";
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.strength *= 1.05;
        this.agility *= 1.05;
    }

}