import java.io.IOException;
import java.util.*;

// Extension of the Game class that implements the methods needed for Heroes and Monster
public class HeroesAndMonstersGame extends Game {

    private Random rand = new Random();
    private static final double BATTLERATE = 0.25;
    HeroSquad party;

    public HeroesAndMonstersGame() throws IOException {
        this.map = new Map();
    }

    public void launchGame(Scanner sc) throws IOException {
        System.out.println("Welcome to Heroes and Monsters");
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
        System.out.println("\n Your party is: " + "\n" + party.toString());
        System.out.println();
        printHelpMessages();
        System.out.println("\n Here is the map for your journey! Good luck! \n" );
        map.printMap();
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

    private void printHelpMessages() {
        System.out.println("===========================================================================================================================================");
        System.out.println("Welcome to the World of Heroes and Monsters. Here, you are able to roam freely in every tile that is not labeled 'X'");
        System.out.println("You will start in the upper left-hand corner of the map. The tile labeled 'H' is where your party currently is");
        System.out.println("On tiles labeled '_', there is a chance each time you step on the tile that you encounter a hoarde of monsters to fight so be ready!");
        System.out.println("On tiles labeled 'M', you can enter a market where you can buy and sell items with a merchant. You can press 'm' to enter the market."); 
        System.out.println("To move around the map, you can use 'w', 'a', 's', and 'd' to move up, left, down, and right respectively.");
        System.out.println("To view information about your party, you can press 'e' to display their stats");
        System.out.println("To view your party's inventories, you can press 'i' to enter the inventory display");
        System.out.println("To quit Heroes and Monsters, you can press 'q' to quit the game from the map.");
        System.out.println("To display this message again, please press 'h' from the map.");
        System.out.println("===========================================================================================================================================");
    }

    public int processMapInput(Scanner sc) {
        System.out.print("What would you like to do? Remember you can press 'h' to display the rules and list of controls again: ");
        char choice = Character.toLowerCase(sc.next().charAt(0));
        int[] currPosition = map.getHeroSquadPosition();
        switch (choice) {
            case ('w'): {
                int[] newPosition = new int[] {currPosition[0]-1, currPosition[1]};
                return moveSquad(newPosition);
            }
            case ('a'): {
                int[] newPosition = new int[] {currPosition[0], currPosition[1]-1};
                return moveSquad(newPosition);
            }
            case ('s'): {
                int[] newPosition = new int[] {currPosition[0]+1, currPosition[1]};
                return moveSquad(newPosition);
            }
            case ('d'): {
                int[] newPosition = new int[] {currPosition[0], currPosition[1]+1};
                return moveSquad(newPosition);
            }
            case ('m'): {
                if (map.getCell(currPosition) instanceof MarketCell) {
                    return 3;
                }
                else {
                    System.out.println("Your location does not have a market. Please go to a market to shop.");
                    return -1;
                }
            }
            case ('e'): {
                return 4;
            }
            case ('i'): {
                return 5;
            }
            case ('h'): {
                return 6;
            }
            case ('q'): {
                return 7;
            }
            default: {
                System.out.println("The input you chose was invalid. Please try again.");
                return -1;
            }
        }
       
    }

    public int moveSquad (int[] newPosition) {
        if (map.moveSquad(newPosition)) {
            if (map.getCell(newPosition) instanceof BattleCell) {
                float roll = rand.nextFloat();
                if (roll < BATTLERATE) {
                    return 2;
                }
            }
            return 1;
        }
        System.out.println("The tile you are trying to reach is either out of the map or inaccessible. Please try again.");
        return -1;
    }
        


    public void playGame(Scanner sc) throws IOException {
        launchGame(sc);
        while (true) {
            int choice = processMapInput(sc);
            switch (choice) {
                case (2): {
                    BattleUI battleWindow = new BattleUI(party);
                    battleWindow.launchInterface(sc);
                    System.out.println("A battle will occur here soon");
                    break;
                }
                case (3): {
                    MarketUI marketWindow = new MarketUI(party, (MarketCell) map.getCell(map.getHeroSquadPosition()));
                    marketWindow.launchInterface(sc);
                    break;
                }
                case (4): {
                    System.out.println("\n Your party is: " + "\n" + party.toString());
                    break;
                }
                case (5): {
                    UserInventoryUI inventoryCheck = new UserInventoryUI(party);
                    inventoryCheck.launchInterface(sc);
                    break;
                }
                case (6): {
                    printHelpMessages();
                    break;
                }
                case (7): {
                    return;
                }
                default: {
                    break;
                }
            }
            map.printMap();
        }
    }
    
}
