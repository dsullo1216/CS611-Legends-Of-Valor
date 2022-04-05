// Subclass of Monster that has special stats
public class Spirit extends Monster {
    
    public Spirit(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
        type = "Spirit";
    }

}