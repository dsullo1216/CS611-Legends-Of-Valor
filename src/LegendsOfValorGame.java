import java.io.IOException;
import java.util.Scanner;

public class LegendsOfValorGame extends RPGGame {

    public LegendsOfValorGame() throws IOException {
        this.map = new LegendsOfValorMap();
    }

    public void printHelpMessages() {
        System.out.println("===========================================================================================================================================");
        System.out.println("Welcome to Legends of Valor. Here, you are able to fight monsters and make it to the Monster's Nexus to win");
        System.out.println("Each of your three heroes will start at the bottom of their respective lane. Each tile where your hero is located will be labeled with HX where X is the number of the hero");
        System.out.println("To move around the map, you can use 'w', 'a', 's', and 'd' to move up, left, down, and right respectively.");
        System.out.println("Additionally, you can teleport to other lanes by pressing 't' or teleport back to your Nexus spawn by pressing 'b'.");
        System.out.println("To view information about your party, you can press 'e' to display their stats");
        System.out.println("To view your party's inventories, you can press 'i' to enter the inventory display");
        System.out.println("To quit Legends of Valor, you can press 'q' to quit the game from the map.");
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

    public int moveSquad (int[] newPosition) {
        if (map.moveSquad(newPosition)) {
            return 1;
        }
        System.out.println("The tile you are trying to reach is either out of the map or inaccessible. Please try again.");
        return -1;
    }

    @Override
    public void playGame(Scanner sc) throws IOException {
        System.out.println("PLEASE CHOOSE 3 CHAMPIONS WHEN STARTING THE GAME IN ORDER TO PROPERLY RUN");
        launchGame(sc, "Legends of Valor");
        while (true) {
            int choice = processMapInput(sc);
            switch (choice) {
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
                case (8): {
                    // TODO Add teleporting
                    break;
                }
                case (9): {
                    // TODO Add return to nexus
                    break;
                }
                default: {
                    break;
                }
            }
            map.printMap();
        }
        
    }
    
}
