import java.io.IOException;
import java.util.*;

public class LegendsOfValorMap implements Map {

    private Random rand = new Random();
    private Cell[][] map;
    private HashMap<Integer, int[]> currHeroPositions;
    private HashMap<Integer, int[]> currMonsterPositions;

    public LegendsOfValorMap() throws IOException {
        this.map = new Cell[8][8];
        initializeMap();
    }

    @Override
    public void printMap() {
        // TODO Auto-generated method stub
        
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
        for (int i = 0; i < map.length; i+=3) {
            this.map[0][i] = new NexusCell(new int[] {0,i});
            this.map[7][i] = new NexusCell(new int[] {7,i});
        }
    }

    public void initializeLanes() {
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (j == 3 || j ==  5) {
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
            default: {
                return (PlainCell) result;
            }
        }
    }

    @Override
    public boolean moveSquad(int[] newPosition, int heroIndex) {
        // TODO Auto-generated method stub
        return false;
    }
    
    // TODO Add method to move monsters
}