import java.io.IOException;
import java.util.*;

public abstract class RPGGame extends Game {

    protected Random rand = new Random();
    HeroSquad party;

    public void launchGame(Scanner sc, String gameName) throws IOException {
        System.out.println("Welcome to " + gameName);
        pickHeroes(sc);
        printHelpMessages();
        System.out.println("\n Here is the map for your journey! Good luck! \n" );
        map.printMap();
    }
    
    public void pickHeroes(Scanner sc) throws IOException {
        System.out.print("How many Heroes would you like to play with? (1-3): ");
        char numHeroesChoice = sc.next().charAt(0);
        while (!"123".contains(numHeroesChoice + "")) {
            System.out.print("Invalid number of Heroes. Please enter a number between 1 and 3: ");
            numHeroesChoice = sc.next().charAt(0);
        }
        int numHeroes = Character.getNumericValue(numHeroesChoice);
        party = new HeroSquad(numHeroes);
        System.out.println("Here is the list of heroes and their starting statistics. You make pick " + numHeroesChoice + " unique heroes.");
        System.out.println("Please note that the order in which you pick your heroes will be the order in which they fight in battle");
        System.out.println();
        Hero[] heroList = ReadFiles.ListOfHeroes();
        System.out.println(Hero.DESCRIPTION);
        for (int i = 0; i < heroList.length; i++) {
            System.out.print(i + ". ");
            System.out.println(heroList[i]);
        }
        getHeroIndices(sc, numHeroes, heroList);
        System.out.println("\n Your party is: " + "\n" + party.toString() + "\n");
    }

    private void getHeroIndices(Scanner sc, int numHeroes, Hero[] heroList) {
        System.out.println("Please select each hero by typing in the number next to their name and pressing enter/return until you've selected " + numHeroes + " hero(es)");
        String currHeroChoice;
        while (!party.isFull()) {
            System.out.print("Please select your hero: ");
            currHeroChoice = sc.next();
            while (Integer.valueOf(currHeroChoice) < 0 && Integer.valueOf(currHeroChoice) > heroList.length) {
                System.out.print("The index you chose is out of range for the list of Heroes. Please try again: ");
                currHeroChoice = sc.next();
            }
            if (party.findEntity(heroList[Integer.valueOf(currHeroChoice)]) != -1) {
                System.out.print("This hero is already a member of your party. Please select a different hero.");
            }
            else {
                party.addEntity(heroList[Integer.valueOf(currHeroChoice)]);
                System.out.println("Successfully added " + heroList[Integer.valueOf(currHeroChoice)].getName() + " to your party!");
            }

        }
    }

    public abstract void printHelpMessages();
    public abstract int processMapInput(Scanner sc);
    public abstract int moveSquad (int[] newPosition);

}