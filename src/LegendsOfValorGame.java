import java.io.IOException;
import java.util.*;

public class LegendsOfValorGame extends RPGGame {

    private HashMap<Integer, int[]> heroSpawns;
    private HashMap<Integer, int[]> monsterSpawns;

    public LegendsOfValorGame() throws IOException {
        this.map = new LegendsOfValorMap();
        setSpawns();
    }

    private void setSpawns() {
        heroSpawns.put(Integer.valueOf(0), new int[] {7,0});
        heroSpawns.put(Integer.valueOf(1), new int[] {7,3});
        heroSpawns.put(Integer.valueOf(2), new int[] {7,6});
        monsterSpawns.put(Integer.valueOf(0), new int[] {0,0});
        monsterSpawns.put(Integer.valueOf(1), new int[] {0,3});
        monsterSpawns.put(Integer.valueOf(2), new int[] {0,6});
    }

    public void printHelpMessages() {
        System.out.println("===========================================================================================================================================");
        System.out.println("Welcome to Legends of Valor. Here, you are able to fight monsters and make it to the Monster's Nexus to win");
        System.out.println("Each of your three heroes will start at the bottom of their respective lane. Each tile where your hero is located will be labeled with HX where X is the number of the hero");
        System.out.println("To move around the map, you can use 'w', 'a', 's', and 'd' to move up, left, down, and right respectively.");
        System.out.println("Additionally, you can teleport to other lanes by pressing 't' or return back to your Nexus spawn by pressing 'b'.");
        System.out.println("To view information about your party, you can press 'e' to display their stats");
        System.out.println("To view your party's inventories, you can press 'i' to enter the inventory display");
        System.out.println("To quit Legends of Valor, you can press 'q' to quit the game from the map.");
        System.out.println("To display this message again, please press 'h' from the map.");
        System.out.println("===========================================================================================================================================");
    }

    public int processMapInput(Scanner sc, int heroIndex) {
        System.out.print("What would you like to do? Remember you can press 'h' to display the rules and list of controls again: ");
        char choice = Character.toLowerCase(sc.next().charAt(0));
        int[] currPosition = ((LegendsOfValorMap)map).getHeroPosition(heroIndex);
        switch (choice) {
            case ('w'): {
                int[] newPosition = new int[] {currPosition[0]-1, currPosition[1]};
                return moveSquad(newPosition, heroIndex);
            }
            case ('a'): {
                int[] newPosition = new int[] {currPosition[0], currPosition[1]-1};
                return moveSquad(newPosition, heroIndex);
            }
            case ('s'): {
                int[] newPosition = new int[] {currPosition[0]+1, currPosition[1]};
                return moveSquad(newPosition, heroIndex);
            }
            case ('d'): {
                int[] newPosition = new int[] {currPosition[0], currPosition[1]+1};
                return moveSquad(newPosition, heroIndex);
            }
            case ('m'): {
                if (map.getCell(currPosition) instanceof MarketCell) { // TODO Change this to NexusCell when it exists
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
            case ('t'): {
                return 8;
            }
            case ('b'): {
                return 9;
            }
            default: {
                System.out.println("The input you chose was invalid. Please try again.");
                return -1;
            }
        }
    }

    public int moveSquad (int[] newPosition, int heroIndex) {
        if (map.moveSquad(newPosition, heroIndex)) {
            // TODO Add method to check for adjacent monster; if there is a monster return 2
            return 1;
        }
        System.out.println("The tile you are trying to reach is either out of the map or inaccessible. Please try again.");
        return -1;
    }

    public int returnToNexus (int heroIndex) {
        return moveSquad(heroSpawns.get(Integer.valueOf(heroIndex)), heroIndex);
    }

    public int teleport (Scanner sc, int heroIndex) {
        System.out.println("Which lane would you like to teleport to? \n 0. Top Lane \n 1. Mid Lane \n 2. Bot Lane");
        System.out.print("Please enter the number for which lane you would like to teleport to: ");
        char actionChoice = sc.next().charAt(0);
        while (Integer.valueOf(actionChoice) < 0 && Integer.valueOf(actionChoice) > 2) {
            System.out.print("Invalid input please try again: ");
            actionChoice = sc.next().charAt(0);
        }
        int choice = Integer.valueOf(actionChoice);
        int currLane = ((LegendsOfValorMap) map).getLane(((LegendsOfValorMap) map).getHeroPosition(heroIndex)[1]);
        if (choice == currLane) {
            System.out.println("You cannot teleport to the lane that you are already in");
            return -1;
        }
        return moveSquad(heroSpawns.get(Integer.valueOf(choice)), heroIndex);
    }

    @Override
    public void playGame(Scanner sc) throws IOException {
        System.out.println("PLEASE CHOOSE 3 CHAMPIONS WHEN STARTING THE GAME IN ORDER TO PROPERLY RUN");
        launchGame(sc, "Legends of Valor");
        while (true) {
            for (int heroIndex = 0; heroIndex < party.size(); heroIndex++) {
                System.out.println(party.getEntityAt(heroIndex).getName() + ", please make your move.");
                int choice = processMapInput(sc, heroIndex);
                switch (choice) {
                    case (2): {
                        Monster currMonster = new Monster("name", 0, 0, 0, 0); // TODO Add method to actually get the instance of the monster nearby
                        BattleUI battleWindow = new BattleUI((Hero) party.getEntityAt(heroIndex), currMonster);
                        if (!battleWindow.launchInterface(sc)) {
                            System.out.println("Your hero has returned to his nexus");
                            // TODO reset the hero's health/mana
                            moveSquad(heroSpawns.get(heroIndex), heroIndex);
                        }
                        else {
                            // TODO call method to move monster 
                        }
                        break;
                    }
                    case (3): {
                        MarketUI marketWindow = new MarketUI(party, (MarketCell) map.getCell(((LegendsOfValorMap)map).getHeroPosition(heroIndex))); 
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
                    case (8): {
                        teleport(sc, heroIndex);
                        break;
                    }
                    case (9): {
                        returnToNexus(heroIndex);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            // TODO Call method to advance monsters one space and initiate fights if need be ONE AT A TIME
            // TODO Add method to add mana/hp buff after each round to all heros
            map.printMap();
        }
        
    }
    
}
