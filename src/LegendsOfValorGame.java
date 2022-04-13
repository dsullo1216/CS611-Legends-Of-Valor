import java.io.IOException;
import java.util.*;

public class LegendsOfValorGame extends RPGGame {

    private HashMap<Integer, int[]> heroSpawns;
    private HashMap<Integer, int[]> monsterSpawns;
    private MonsterSquad hoard;
    
//    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_RED = "\u001B[31m";

    public LegendsOfValorGame() throws IOException {
        this.map = new LegendsOfValorMap();
        this.heroSpawns = new HashMap<Integer, int[]>();
        this.monsterSpawns = new HashMap<Integer, int[]>();
        this.hoard = new MonsterSquad(3);
        hoard.randomizeMonsterSquad();
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
                if (map.getCell(currPosition) instanceof NexusCell) {
                    return 3;
                }
                else {
//                	System.out.println(ANSI_RED + "Your location does not have a market. Please go to a market to shop." + ANSI_RESET);
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
        int[] oldPosition = ((LegendsOfValorMap)map).getHeroPosition(heroIndex).clone();
        int[] shiftedOver = ((LegendsOfValorMap)map).moveOverCell(newPosition);
        if (shiftedOver[0] == -1 && shiftedOver[1] == -1) {
            System.out.println("The tile you are trying to reach is either out of the map, full, or inaccessible. Please try again.");
        	return -1;
        }
        else {
        	newPosition = shiftedOver;
        }
    	if (map.moveSquad(newPosition, heroIndex)) {
            if (((LegendsOfValorMap) map).checkAdjacentMonsters(newPosition[0], newPosition[1]) != -1) {
                return 2;
            }
        	// Take away the boost from the old cell
        	if (map.getCell(oldPosition) instanceof BuffCell) {
        		((BuffCell) map.getCell(oldPosition)).revertBoostedStat( (Hero) party.getEntityAt(heroIndex));
        	}	
        	// Apply new boost based on the new cell 
        	if (map.getCell(newPosition) instanceof BuffCell) {
        		((BuffCell) map.getCell(newPosition)).boostStat( (Hero) party.getEntityAt(heroIndex));
        	}
            return 1;
        }
        System.out.println("The tile you are trying to reach is either out of the map, full, or inaccessible. Please try again.");
        return -1;
    }

    public int returnToNexus (int heroIndex) {
        return moveSquad(heroSpawns.get(Integer.valueOf(heroIndex)), heroIndex);
    }

    public int teleport (Scanner sc, int heroIndex) {
        System.out.println("Which lane would you like to teleport to? \n 0. Top Lane \n 1. Middle Lane \n 2. Bottom Lane");
        System.out.print("Please enter the number for which lane you would like to teleport to: ");
        char actionChoice = sc.next().charAt(0);
        while (Integer.valueOf(actionChoice) < 0 && Integer.valueOf(actionChoice) > 2) {
            System.out.print("Invalid input please try again: ");
            actionChoice = sc.next().charAt(0);
        }
        int choice = Character.getNumericValue(actionChoice);
        int currLane = ((LegendsOfValorMap) map).getLane(((LegendsOfValorMap) map).getHeroPosition(heroIndex)[1]);
        if (choice == currLane) {
            System.out.println("You cannot teleport to the lane that you are already in");
            return -1;
        }
        return moveSquad(heroSpawns.get(Integer.valueOf(choice)), heroIndex);
    }

    @Override
    public void playGame(Scanner sc) throws IOException {
        launchGame(sc, "Legends of Valor");
        boolean noWinner = true;
        while (noWinner) {
            for (int heroIndex = 0; heroIndex < party.size(); heroIndex++) {
                System.out.println(party.getEntityAt(heroIndex).getName() + " (H" + heroIndex + ")" + ", please make your move.");
                int choice = processMapInput(sc, heroIndex);
                while (choice == -1) {
                	choice = processMapInput(sc, heroIndex);
                }
                switch (choice) {
                    case (2): {
                        int[] heroPosition = ((LegendsOfValorMap) map).getHeroPosition(heroIndex);
                        int currMonsterIndex = ((LegendsOfValorMap) map).checkAdjacentMonsters(heroPosition[0], heroPosition[1]);
                        Monster currMonster = (Monster) hoard.getEntityAt(currMonsterIndex);
                        BattleUI battleWindow = new BattleUI((Hero) party.getEntityAt(heroIndex), currMonster);
                        if (!battleWindow.launchInterface(sc)) {
                            System.out.println("Your hero has returned to their nexus after being killed");
                            returnToNexus(heroIndex);
                        }
                        else {
                            ((LegendsOfValorMap) map).removeMonster(currMonsterIndex);
                            currMonster.updateIsDead();
                        }
                        break;
                    }
                    case (3): {
                        MarketUI marketWindow = new MarketUI(party, (MarketCell) map.getCell(((LegendsOfValorMap)map).getHeroPosition(heroIndex))); 
                        marketWindow.launchInterface(sc);
                        heroIndex--; //allow the hero to do more in their turn
                        break;
                    }
                    case (4): {
                        System.out.println("\n Your party is: " + "\n" + party.toString());
                        heroIndex--; //allow the hero to do more in their turn
                        break;
                    }
                    case (5): {
                        UserInventoryUI inventoryCheck = new UserInventoryUI(party);
                        inventoryCheck.launchInterface(sc);
                        heroIndex--; //allow the hero to do more in their turn
                        break;
                    }
                    case (6): {
                        printHelpMessages();
                        heroIndex--; //allow the hero to do more in their turn
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
                map.printMap();
            }
            for (int monsterIndex=0; monsterIndex < monsterSpawns.size(); monsterIndex++) {
                Monster currMonster = (Monster) hoard.getEntityAt(monsterIndex);
                if (currMonster.getIsDead() && currMonster.getRoundsDead() == 8) {
                    currMonster.updateIsDead();
                    currMonster.updateRoundsDead(0);
                    ((LegendsOfValorMap)map).setMonster(monsterIndex, monsterSpawns.get(monsterIndex));
                }
                else if (currMonster.getIsDead()) {
                    currMonster.updateRoundsDead(currMonster.getRoundsDead()+1);
                }
                else {
                int[] currPosition = ((LegendsOfValorMap)map).getMonsterPosition(monsterIndex);
                int[] newPosition = new int[] {currPosition[0]+1, currPosition[1]};
                noWinner = !((LegendsOfValorMap)map).moveMonster(newPosition, monsterIndex);
                System.out.println("Monster " + monsterIndex + " has advanced! Be careful!"); 
                int nearbyHero = ((LegendsOfValorMap) map).checkAdjacentHeros(newPosition[0], newPosition[1]);
                if (nearbyHero != -1) {
                	map.printMap();
                	System.out.println();
                    currMonster = (Monster) hoard.getEntityAt(monsterIndex);
                    BattleUI battleWindow = new BattleUI((Hero) party.getEntityAt(nearbyHero), currMonster);
                    if (!battleWindow.launchInterface(sc)) {
                        System.out.println("Your hero has returned to their nexus after being killed");
                        returnToNexus(nearbyHero);
                    }
                    else {
                        ((LegendsOfValorMap) map).removeMonster(monsterIndex);
                        currMonster.updateIsDead();
                    }

                }
            }
            
            // HP and Mana regeneration after each round for all heros
            heroesRegain();

            
            System.out.println();
            map.printMap();
        }
        System.out.println("A winner has been declared! Good game!");
        }
    }
    
    // Every round, allow the heroes to regain 10% of their hp and 10% of their mana.
    public void heroesRegain() {
        for (int heroIndex = 0; heroIndex < party.size(); heroIndex++) {
        	Hero heroToRegain = (Hero) party.getEntityAt(heroIndex);
        	heroToRegain.updateHP( (int) (heroToRegain.getHP() * 1.10) );
        	heroToRegain.updateMana( (int) (heroToRegain.getMana() * 1.10) );
        }
    }
    
}
