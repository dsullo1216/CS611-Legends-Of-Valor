import java.io.IOException;
import java.util.Scanner;

// Abstract class that includes functions/headers for all other UI classes
public abstract class UserInterface {

    HeroSquad party;
    
    public abstract int chooseAction(Scanner sc);

    public abstract void launchInterface(Scanner sc) throws IOException;

    public Hero chooseHero(Scanner sc) {
        System.out.println("Please select which hero's inventory you would like to manage by entering the number next to their name:");
        for (int i = 0; i < party.size(); i++) {
            System.out.print(i + ". ");
            System.out.println(party.getEntityAt(i));
        }
        System.out.print("Please select your hero: ");
        char heroChoice = sc.next().charAt(0);
        while (Integer.valueOf(heroChoice) < 0 && Integer.valueOf(heroChoice) >= party.size()) {
            System.out.print("The index you chose is out of range for the list of Heroes. Please try again: ");
            heroChoice = sc.next().charAt(0);
        }
        return (Hero) party.getEntityAt(Character.getNumericValue(heroChoice));
    }

}