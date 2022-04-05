// Specialized version of the Monster class that includes title
public class Exoskeleton extends Monster {
    
    public Exoskeleton(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
        type = "Exoskeleton";
    }

}