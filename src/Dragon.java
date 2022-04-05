// Specialized version of Monster that includes title
public class Dragon extends Monster {
    
    public Dragon(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);
        type = "Dragon";
    }

}