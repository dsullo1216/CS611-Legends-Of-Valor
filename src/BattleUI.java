import java.io.IOException;
import java.util.*;

// Subclass of UserInterface that represents User Interface where Battle occurs. Implements all necessary methods
public class BattleUI extends UserInterface {

    private Random rand = new Random();
    private MonsterSquad hoard;
    private Hero currHero;
    private Monster currMonster;

    public BattleUI(HeroSquad party) throws IOException {
        this.party = party;
        this.hoard = new MonsterSquad(party.size());
        hoard.randomizeMonsterSquad();
        currHero = (Hero) party.getEntityAt(0);
        currMonster = (Monster) hoard.getEntityAt(0);

    }

    private int chooseSpell(Scanner sc) {
        Inventory currInventory = currHero.getInventory();
        Inventory spellInventory = new Inventory(currInventory.size());
        for (int i = 0; i < currInventory.size(); i++) {
            if (currInventory.getItemAt(i) instanceof Spell) {
                spellInventory.addItem(currInventory.getItemAt(i));
            }
        }
        int i;
        for (i = 0; i < spellInventory.size(); i++) {
            if (spellInventory.getItemAt(i) == null) {
                break;
            }
            System.out.print(i + ". ");
            System.out.println(spellInventory.getItemAt(i));
        }
        if (i == 0) {
            System.out.println("There are no spells in your inventory");
            return -1;
        }
        System.out.println("Please choose which spell you would like to use from the list above");
        System.out.println("No / " + Spell.DESCRIPTION);
        System.out.print("Please select which spell to use");
        String spellChoice = sc.next();
        while (Integer.valueOf(spellChoice) < 0 && Integer.valueOf(spellChoice) >= i) {
            System.out.print("The index you chose is out of range for the list of spells. Please try again: ");
            spellChoice = sc.next();
        }
        return Integer.valueOf(spellChoice);
    }

    private boolean useSpell(Scanner sc) {
        Inventory currInventory = currHero.getInventory();
        Inventory spellInventory = new Inventory(currInventory.size());
        for (int i = 0; i < currInventory.size(); i++) {
            if (currInventory.getItemAt(i) instanceof Spell) {
                spellInventory.addItem(currInventory.getItemAt(i));
            }
        }
        int chosenSpell = chooseSpell(sc);
        if (chosenSpell == -1) {
            return false;
        }
        Spell spellToUse = (Spell) spellInventory.getItemAt(chosenSpell);
        double damageDealt = (currHero.getDexterity() / 10000) * spellToUse.getDamage();
        if (currHero.getMana() < spellToUse.getMana()) {
            System.out.println("========================================================");
            System.out.println("You do not have enough mana to use this spell. It did nothing!");
            System.out.println("========================================================");
            return false;
        }
        currMonster.updateHP((int) (currMonster.getHP() - damageDealt));
        currHero.updateMana(currHero.getMana() - spellToUse.getMana());
        currHero.getInventory().removeItem(spellToUse);
        System.out.println("========================================================");
        System.out.println("You cast a spell on the monster using your " + spellToUse.getName() + "!");
        System.out.println(currHero.getName() + " dealt " + damageDealt + " damage to " + currMonster.getName());
        System.out.println("========================================================");
        if (currMonster.getHP() < 0) {
            System.out.println("You knocked out " + currMonster.getName() + "!");
            restoreStats();
            return true;
        }
        switch (spellToUse.getReducedStat()) {
            case ("damage"): {
                currMonster.updateDamage((int) (currMonster.getDamage() * 0.9));
                break;
            }
            case ("defense"): {
                currMonster.updateDefense((int) (currMonster.getDefense() * 0.9));
                break;
            }
            case ("dodge_chance"): {
                currMonster.updateDodgeChance((int) (currMonster.getDodgeChance() * 0.9));
                break;
            }
            default: {
                break;
            }
        }
        return false;
    }

    private boolean attackMonster() {
        double dodgeChance = currMonster.getDodgeChance() * 0.01;
        float roll = rand.nextFloat();
        if (roll < dodgeChance) {
            System.out.println("========================================================");
            System.out.println("The monster evaded your attack!");
            System.out.println(currHero.getName() + " dealt 0 damage to " + currMonster.getName());
            System.out.println("========================================================");
            return false;
        }
        else {
            double damageDealt = (currHero.getStrength() + currHero.getWeapon().getDamage()) * 0.05;
            currMonster.updateHP((int) (currMonster.getHP() - damageDealt));
            System.out.println("========================================================");
            System.out.println("You attacked the monster using your " + currHero.getWeapon().getName() + "!");
            System.out.println(currHero.getName() + " dealt " + damageDealt + " damage to " + currMonster.getName());
            System.out.println("========================================================");
            if (currMonster.getHP() < 0) {
                System.out.println("You knocked out " + currMonster.getName() + "!");
                restoreStats();
                return true;
            }
            return false;
        }
    }

    private boolean attackHero() {
        double dodgeChance = currHero.getAgility() * 0.0002;
        float roll = rand.nextFloat();
        if (roll < dodgeChance) {
            System.out.println("========================================================");
            System.out.println("You evaded the monster's attack!");
            System.out.println(currMonster.getName() + " dealt 0 damage to " + currHero.getName());
            System.out.println("========================================================");
            return false;
        }
        else {
            double damageDealt = (currMonster.getDamage() - currHero.getArmor().getDefense());
            if (damageDealt < 0) {
                System.out.println("========================================================");
                System.out.println("Your armor absorbed the monster's attack!");
                System.out.println(currMonster.getName() + " dealt 0 damage to " + currHero.getName());
                System.out.println("========================================================");
                return false;
            }
            else {
                currHero.updateHP((int) (currHero.getHP() - damageDealt));
                System.out.println("========================================================");
                System.out.println("The monster successfully attacked you!");
                System.out.println(currMonster.getName() + " dealt " + damageDealt + "damage to " + currHero.getName());
                System.out.println("========================================================");
                if (currHero.getHP() < 0) {
                    System.out.println("The monster knocked out your current hero!");
                    return true;
                }
                return false;
            }
            
        }
    }

    private void displayCurrHero() {
        System.out.println("========================================================");
        System.out.println("Here are the current stats for your current hero: ");
        System.out.println(Hero.DESCRIPTION);
        System.out.println(currHero);
        System.out.println("========================================================");
    }

    private void displayCurrMonster() {
        System.out.println("========================================================");
        System.out.println("Here are the current stats for the current monster: ");
        System.out.println(Monster.DESCRIPTION);
        System.out.println(currMonster);
        System.out.println("========================================================");
    }

    private boolean nextHero() {
        int nextHeroIndex = party.nextAlive();
        if (nextHeroIndex == -1) {
            System.out.println("Your entire party has been eliminated and you've lost the game.");
            return false;
        }
        currHero = (Hero) party.getEntityAt(nextHeroIndex);
        return true;
    }

    private boolean nextMonster() {
        int nextMonsterIndex = hoard.nextAlive();
        if (nextMonsterIndex == -1) {
            System.out.println("You have eliminated the entire enemy hoarde and won the battle!");
            return false;
        }
        currMonster = (Monster) hoard.getEntityAt(nextMonsterIndex);
        return true;
    }

    private void restoreStats() {
        currHero.updateHP(currHero.getHP() + (currHero.getLevel() * 50));
        currHero.updateMana(currHero.getMana() + 400);
    }

    private boolean finishBattle(boolean HerosWon) {
        if (HerosWon == false) {
            System.out.println("Game Over. Thank you for playing!");
            
        }
        else {
            for (int i = 0; i < party.size(); i++) {
                if (party.getEntityAt(i).getHP() < 0) {
                    party.getEntityAt(i).updateHP(party.getEntityAt(i).getLevel() * 50);
                }
                else {
                    ((Hero) party.getEntityAt(i)).updateWallet( ((Hero) party.getEntityAt(i)).getWallet() + 500);
                    ((Hero) party.getEntityAt(i)).updateEXP( ((Hero) party.getEntityAt(i)).getEXP() + 2);
                }
            }
        }
        return HerosWon;
    }

    @Override
    public int chooseAction(Scanner sc) {
        System.out.println("Please choose an option from the list below to make your move");
        System.out.println("0. Attack \n 1. Use a Spell \n 2. Manage your inventory to change armor or use a potion");
        System.out.println("Please make a choice: ");
        char actionChoice = sc.next().charAt(0);
        while (Integer.valueOf(actionChoice) < 0 && Integer.valueOf(actionChoice) > 2) {
            System.out.print("Invalid input please try again: ");
            actionChoice = sc.next().charAt(0);
        }
        return Character.getNumericValue(actionChoice);
    }

    public boolean battleOperations(Scanner sc) throws IOException {
        int actionChoice = chooseAction(sc);
        switch (actionChoice) {
            case (0): {
                return attackMonster();
            }
            case (1): {
                return useSpell(sc);
            }
            case (2): {
                UserInventoryUI inventoryCheck = new UserInventoryUI(party);
                inventoryCheck.launchInterface(sc);
                break;
            }
            default: {
                break;
            }
        }
        return false;
    }

    @Override
    public void launchInterface(Scanner sc) throws IOException {
        System.out.println("You have encounter a hoarde of monsters! Be prepared to fight them!");
        boolean finished = false;
        while (!finished) {
            
            displayCurrHero();

            displayCurrMonster();

            if (battleOperations(sc)) {
                if (!nextMonster()) {
                    finished = true;
                }
            }

            if (attackHero()) {
                if (!nextHero()) {
                    finished = true;
                }
            }

        }
        
    }
    
}
