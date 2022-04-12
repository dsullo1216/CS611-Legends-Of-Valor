import java.io.IOException;
import java.util.*;

public class LegendsOfValorMap implements Map {

    private Random rand = new Random();
    private Cell[][] map;
    private HashMap<Integer, int[]> currHeroPositions;
    private HashMap<Integer, int[]> currMonsterPositions;

    public LegendsOfValorMap() throws IOException {
        this.map = new Cell[8][8];
        this.currHeroPositions = new HashMap<Integer, int[]>();
        this.currMonsterPositions = new HashMap<Integer, int[]>();
        initializeMap();
    }

    @Override
    public void printMap() {
        // TODO Auto-generated method stub
    	for (int i = 0; i < map.length; i++) {
    		for (int j = 0; j < map.length; j++) {
        		System.out.print( map[i][j].getSymbol() + " - " + map[i][j].getSymbol() + " - " + map[i][j].getSymbol() + "   ");
    		}
    		System.out.println();
    		for (int j = 0; j < map.length; j++) {
    			if ( map[i][j].getSymbol() == 'X' ) {
    				System.out.print("|XXXXXXX|   ");
    			}                             
    			else {
    				System.out.print("|" + heroOrMonsterString(i, j) + "|   ");
    			}
    		}
    		System.out.println();
    		for (int j = 0; j < map.length; j++) {
        		System.out.print( map[i][j].getSymbol() + " - " + map[i][j].getSymbol() + " - " + map[i][j].getSymbol() + "   ");
    		}
    		System.out.println();
    		System.out.println();
    	}        
    }
        
    @Override
    public void initializeMap() throws IOException {
        initializePositions();
        initializeInaccessibles();
        initializeNexuses();
        initializeLanes();
    }

    private void initializePositions() {
        currHeroPositions.put(Integer.valueOf(0), new int[] {7,0});
        currHeroPositions.put(Integer.valueOf(1), new int[] {7,3});
        currHeroPositions.put(Integer.valueOf(2), new int[] {7,6});
        currMonsterPositions.put(Integer.valueOf(0), new int[] {0,0});
        currMonsterPositions.put(Integer.valueOf(1), new int[] {0,3});
        currMonsterPositions.put(Integer.valueOf(2), new int[] {0,6});
    }

    public void initializeInaccessibles() {
        for (int i = 0; i < map.length; i++) {
            this.map[i][2] = new InaccessibleCell(new int[] {i,2});
            this.map[i][5] = new InaccessibleCell(new int[] {i,5});
        }
    }

    public void initializeNexuses() throws IOException {
        int[] nexusIndices = {0,1,3,4,6,7};
    	for (int i: nexusIndices) {
            this.map[0][i] = new NexusCell(new int[] {0,i});
            this.map[7][i] = new NexusCell(new int[] {7,i});
        }
    }

    public void initializeLanes() {
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (j == 2 || j ==  5) {
                    continue;
                }
                float roll = rand.nextFloat();
                int[] coord = new int[] {i,j};
                if (roll < 0.20) {
                    this.map[i][j] = new BushCell(coord);
                }
                else if (roll < 0.40) {
                    this.map[i][j] = new CaveCell(coord);
                }
                else if (roll < 0.60) {
                    this.map[i][j] = new KoulouCell(coord);
                }
                else {
                    this.map[i][j] = new PlainCell(coord);
                }
            }
        }
    }

    public int[] getHeroPosition(int heroIndex) {
        return currHeroPositions.get(heroIndex);
    }
    
    // Check if there are heroes (if any) at the specified cell and return the index of the one there 
    // or -1 if no heroes are there 
    public int checkIfHerosHere(int i, int j) {
    	int[] currentCell = {i,j};
    	if ( Arrays.equals(getHeroPosition(0), currentCell) ) {
    		return 0;
    	} else if ( Arrays.equals(getHeroPosition(1), currentCell) ) {
    		return 1;
    	} else if ( Arrays.equals(getHeroPosition(2), currentCell) ) {
    		return 2;
    	} else {
    		return -1;
    	}
    }
    
    public int[] getMonsterPosition(int monsterIndex) {
        return currMonsterPositions.get(monsterIndex);
    }
    
    // Check if there are monsters (if any) at the specified cell and return the index of the one there 
    // or -1 if no monsters are there
    public int checkIfMonstersHere(int i, int j) {
    	int[] currentCell = {i,j};
    	if ( Arrays.equals(getMonsterPosition(0), currentCell) ) {
    		return 0;
    	} else if ( Arrays.equals(getMonsterPosition(1), currentCell) ) {
    		return 1;
    	} else if ( Arrays.equals(getMonsterPosition(2), currentCell) ) {
    		return 2;
    	} else {
    		return -1;
    	}
    }
    
    // Construct the string that each cell would contain to show which heroes and monsters are there
    // Makes sure the presence of 1 monster and 1 hero in a cell don't overlap on the map
    public String heroOrMonsterString(int i, int j) {
    	int whichHeroHere = checkIfHerosHere(i, j);
    	int whichMonsterHere = checkIfMonstersHere(i, j);
    	char[] allHere = {' ', ' ', ' ', ' ', ' ', ' ', ' '};
    	if (whichHeroHere != -1) {
    		allHere[1] = 'H';
    		allHere[2] = String.valueOf(whichHeroHere).charAt(0);
    	} 
    	if (whichMonsterHere != -1) {
    		allHere[4] = 'M';
    		allHere[5] = String.valueOf(whichMonsterHere).charAt(0);
    	}
    	return new String(allHere);
    }

    public int getLane(int col) {
        if (col < 2) {
            return 0;
        }
        else if (col < 5) {
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public Cell getCell(int[] position) {
        Cell result = map[position[0]][position[1]];
        switch (result.getType()) {
            case ("Bush"): {
                return (BushCell) result;
            }
            case ("Cave"): {
                return (CaveCell) result;
            }
            case ("Koulou"): {
                return (KoulouCell) result;
            }
            case ("Nexus"): {
            	return (NexusCell) result;
            }
            case ("Inaccessible"): {
            	return (InaccessibleCell) result;
            }
            default: {
                return (PlainCell) result;
            }
        }
    }

    @Override
    public boolean moveSquad(int[] newPosition, int heroIndex) {
        // TODO Auto-generated method stub
		if (newPosition[0] < 0 || newPosition[1] < 0 || newPosition[0] >= map.length || newPosition[1] >= map[0].length) {
			System.out.println("This position is out of bounds.");
		    return false;
		}   
        if (getCell(newPosition).getType().equals("Inaccessible")) {
            System.out.println("This cell is inaccessible");
            return false;
        }
        removeHero(heroIndex);
        setHero(heroIndex, newPosition);
        return true;    
    }
    
    public void removeHero(int index) {
        int[] heroCurrPosition = currHeroPositions.get(index);
    	((AccessibleCell) this.map[heroCurrPosition[0]][heroCurrPosition[1]]).removeHeroSquadHere();
    }
    
    public void setHero(int index, int[] newPosition) {
        ((AccessibleCell) this.map[newPosition[0]][newPosition[1]]).setHeroSquadHere();
        int[] oldPosition = currHeroPositions.get(index);
        currHeroPositions.replace(index, oldPosition, newPosition);
    }
    
    // TODO Add method to move monsters
}