import java.io.IOException;
import java.util.Scanner;

// Subclass of UserInterface class to represent User Interface where Users can manage their inventory. Includes all necessary methods
public class UserInventoryUI extends UserInterface {

    public UserInventoryUI(HeroSquad party) {
        this.party = party;
    }
    
    public int chooseAction(Scanner sc) {
        System.out.println("Please choose an option from the list below to manage your inventory");
        System.out.println("0. Change Weapon \n 1. Change Armor \n 2. Use Potion");
        System.out.print("Please make a choice: ");
        char actionChoice = sc.next().charAt(0);
        while (Integer.valueOf(actionChoice) < 0 && Integer.valueOf(actionChoice) > 2) {
            System.out.print("Invalid input please try again: ");
            actionChoice = sc.next().charAt(0);
        }
        return Character.getNumericValue(actionChoice);
    }

    public void changeWeapon(Scanner sc, Hero currHero) {
        Inventory currInventory = currHero.getInventory();
        Inventory weaponInventory = new Inventory(currInventory.size());
        for (int i = 0; i < currInventory.size(); i++) {
            if (currInventory.getItemAt(i) instanceof Weapon) {
                weaponInventory.addItem(currInventory.getItemAt(i));
            }
        }
        int i;
        for (i = 0; i < weaponInventory.size(); i++) {
            if (weaponInventory.getItemAt(i) == null) { 
                break; 
            }
            System.out.print(i + ". ");
            System.out.println(weaponInventory.getItemAt(i));
        }
        if (i == 0) {
            System.out.println("You currently have no weapons in your inventory. Please buy weapons at the market first.");
            return;
        }
        System.out.println("Please choose which weapon you would like to equip from the list below. By equiping a weapon, your current weapon will go back to your inventory.");
        System.out.println("No / " + Weapon.DESCRIPTION);
        System.out.print("Please select your weapon to equip: ");
        String weaponChoice = sc.next();
        while (Integer.valueOf(weaponChoice) < 0 && Integer.valueOf(weaponChoice) >= i) {
            System.out.print("The index you chose is out of range for the list of weapons. Please try again: ");
            weaponChoice = sc.next();
        }
        Weapon chosenWeapon = (Weapon) weaponInventory.getItemAt(Integer.valueOf(weaponChoice));
        System.out.println("You have chosen to swap your " + currHero.getWeapon().getName() + " and equip your " + chosenWeapon.getName());
        System.out.println();
        currHero.getInventory().removeItem(chosenWeapon);
        currHero.getInventory().addItem(currHero.getWeapon());
        currHero.updateWeapon(chosenWeapon);
    }

    public void changeArmor(Scanner sc, Hero currHero) {
        Inventory currInventory = currHero.getInventory();
        Inventory armorInventory = new Inventory(currInventory.size());
        for (int i = 0; i < currInventory.size(); i++) {
            if (currInventory.getItemAt(i) instanceof Armor) {
                armorInventory.addItem(currInventory.getItemAt(i));
            }
        }
        int i;
        for (i = 0; i < armorInventory.size(); i++) {
            if (armorInventory.getItemAt(i) == null) { 
                break; 
            }
            System.out.print(i + ". ");
            System.out.println(armorInventory.getItemAt(i));
        }
        if (i == 0) {
            System.out.println("You currently have no armor in your inventory. Please buy armor at the market first.");
            return;
        }
        System.out.println("Please choose which armor you would like to equip from the list below. By equiping armor, your current armor will go back to your inventory.");
        System.out.println("No / " + Armor.DESCRIPTION);
        System.out.print("Please select your armor to equip: ");
        String armorChoice = sc.next();
        while (Integer.valueOf(armorChoice) < 0 && Integer.valueOf(armorChoice) >= i) {
            System.out.print("The index you chose is out of range for the list of armor. Please try again: ");
            armorChoice = sc.next();
        }
        Armor chosenArmor = (Armor) armorInventory.getItemAt(Integer.valueOf(armorChoice));
        System.out.println("You have chosen to swap your " + currHero.getArmor().getName() + " and equip your " + chosenArmor.getName());
        System.out.println();
        currHero.getInventory().removeItem(chosenArmor);
        currHero.getInventory().addItem(currHero.getArmor());
        currHero.updateArmor(chosenArmor);
    }

    public void usePotion(Scanner sc, Hero currHero) {
        Inventory currInventory = currHero.getInventory();
        Inventory potionInventory = new Inventory(currInventory.size());
        for (int i = 0; i < currInventory.size(); i++) {
            if (currInventory.getItemAt(i) instanceof Potion) {
                potionInventory.addItem(currInventory.getItemAt(i));
            }
        }
        int i;
        for (i = 0; i < potionInventory.size(); i++) {
            if (potionInventory.getItemAt(i) == null) { 
                break; 
            }
            System.out.print(i + ". ");
            System.out.println(potionInventory.getItemAt(i));
        }
        if (i == 0) {
            System.out.println("You currently have no potions in your inventory. Please buy potions at the market first.");
            return;
        }
        System.out.println("Please choose which potion you would like to use. By using it, it will be consumed and removed from your inventory");
        System.out.println("No / " + Potion.DESCRIPTION);
        System.out.print("Please select your potion to use: ");
        String potionChoice = sc.next();
        while (Integer.valueOf(potionChoice) < 0 && Integer.valueOf(potionChoice) >= i) {
            System.out.print("The index you chose is out of range for the list of armor. Please try again: ");
            potionChoice = sc.next();
        }
        Potion chosenPotion = (Potion) potionInventory.getItemAt(Integer.valueOf(potionChoice));
        System.out.println("You have chosen to use your " + chosenPotion.getName());
        System.out.println();
        currHero.usePotion(chosenPotion);
        currHero.getInventory().removeItem(chosenPotion);
    }

    public void manageInventory(Scanner sc, Hero currHero) {
      int actionChoice = chooseAction(sc);
      switch (actionChoice) {
          case (0): {
              changeWeapon(sc, currHero);
              break;
          }
          case (1): {
              changeArmor(sc, currHero);
              break;
          }
          case (2): {
              usePotion(sc, currHero);
              break;
          }
          default: {
              break;
          }
      }
    }

    @Override
    public void launchInterface(Scanner sc) throws IOException {
        System.out.println("Welcome to your inventory. Here you can select a hero and view their items and switch the equipment that you are using.");
        boolean run = true;
        while (run) {
            Hero currHero = chooseHero(sc);
            manageInventory(sc, currHero);
            System.out.print("Would you like to check your inventory again? Please enter either 'Y' or 'N' to choose: ");
            char runChoice = sc.next().charAt(0);
            while (runChoice != 'Y' && runChoice != 'N') {
                System.out.print("Invalid input. Please enter either 'Y' or 'N' to chose: ");
                runChoice = sc.next().charAt(0);
            }
            if (runChoice == 'N') {
                run = false;
            }
        }
    }
    
}