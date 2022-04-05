import java.io.IOException;

// Subclass of Entity to represent Heroes. Will be extended by special types of Heroes.
public class Hero extends Entity {

    public static final String DESCRIPTION = "Type    /   Name    /   Level    /   HP    /   Mana    /   Defense    /   Strength    /   Agility    /   Dexterity    /   Wallet";
    protected int exp;
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;
    protected int wallet;
    protected Weapon weapon;
    protected Armor armor;
    protected Inventory inventory;

    public Hero(String name, int mana, int strength, int agility, int dexterity, int wallet, int level) throws IOException {
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.wallet = wallet;
        this.level = level;
        this.hp = level * 100;
        this.exp = 0;
        this.weapon = (Weapon) ReadFiles.ListOfItems()[6];
        this.armor = (Armor) ReadFiles.ListOfItems()[0];
        this.inventory = new Inventory(10);
    }

    public String toString() {
        return type + " / " + 
               name + " / " + 
               Integer.toString(level) + " / " + 
               Integer.toString(hp) + " / " + 
               Integer.toString(mana) + " / " + 
               Integer.toString(armor.getDefense()) + " / " + 
               Integer.toString(strength) + " / " + 
               Integer.toString(agility) + " / " + 
               Integer.toString(dexterity) + " / " +
               Integer.toString(wallet);
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof Hero)) {
            return false;
        }
        
        Hero otherH = (Hero) other;
        return (this.name == otherH.getName());

    }

    public int getEXP() {
        return exp;
    }

    public int updateEXP(int newEXP) {
        this.exp = newEXP;
        if (this.exp > (this.level * 10)) {
            levelUp();
        }
        return exp;
    }

    public String getType() {
        return type;
    }

    public int getMana() {
        return mana;
    }

    public int updateMana(int newMana) {
        this.mana = newMana;
        return newMana;
    }

    public int getStrength() {
        return strength;
    }

    public int updateStrength(int newStrength) {
        this.strength = newStrength;
        return newStrength;
    }

    public int getAgility() {
        return agility;
    }

    public int updateAgility(int newAgility) {
        this.agility = newAgility;
        return newAgility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int updateDexterity(int newDexterity) {
        this.dexterity = newDexterity;
        return newDexterity;
    }

    public int getWallet() {
        return wallet;
    }

    public int updateWallet(int newWallet) {
        this.wallet = newWallet;
        return wallet;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Weapon updateWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Armor updateArmor(Armor newArmor) {
        this.armor = newArmor;
        return armor;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void usePotion(Potion potion) {
        String[] buffedStats = potion.getBuffedStat().split("/");
        int buffAmt = potion.getBuffAmt();
        for (int i = 0; i < buffedStats.length; i++) {
            String buffedStat = buffedStats[0];
            switch (buffedStat) {
                case ("Health"): {
                    updateHP(hp + buffAmt);
                    break;
                }
                case ("Strength"): {
                    updateStrength(strength + buffAmt);
                    break;
                }
                case ("Mana"): {
                    updateMana(mana + buffAmt);
                    break;
                }
                case ("Agility"): {
                    updateAgility(agility + buffAmt);
                    break;
                }
                case ("Dexterity"): {
                    updateDexterity(dexterity + buffAmt);
                    break;
                }
                default: {
                    System.out.println();
                    break;
                }
            }
        }
    }

    public void levelUp() {
        this.level += 1;
        this.exp = 0;
        this.hp = level * 100;
        this.mana *= 1.1;
        this.strength *= 1.05;
        this.agility *= 1.05;
        this.dexterity *= 1.05;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

}