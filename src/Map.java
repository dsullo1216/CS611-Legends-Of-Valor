import java.io.IOException;
import java.util.Random;

// Collection class that holds instances of Cells to represent the map. Includes methods to randomize the map.
public class Map {

    private Random rand = new Random();
    private Cell[][] map;
    private int[] currPosition;

    public Map() throws IOException {
        this.map = new Cell[8][8];
        this.map[0][0] = new BattleCell(new int[] {0,0});
        ((AccessibleCell) this.map[0][0]).setSquadHere();
        currPosition = new int[] {0,0};
        randomizeMap();
    }

    public Map(int mapSize) throws IOException {
        this.map = new Cell[mapSize][mapSize];
        randomizeMap();
    }

    public Map(int mapLength, int mapWidth) throws IOException {
        this.map = new Cell[mapLength][mapWidth];
        randomizeMap();
    }

    public void printMap() {

        for (int i = 0; i < map.length; i++) {
            System.out.printf("==========");
        }
        System.out.println();
        for(int i = 0; i < map[0].length; i++) {
            System.out.println();
            System.out.printf("   ||   ");
            for(int j = 0; j < map.length; j++) {
                System.out.print( map[i][j].getSymbol());
                System.out.print("   ||   ");
            }
            System.out.println();
            System.out.printf(" ");
            System.out.println();
            for(int j = 0; j < map.length; j++) {
                System.out.printf("==========");
            }
            System.out.println();
        }
        
    }

    private void randomizeMap() throws IOException {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                float roll = rand.nextFloat();
                int[] coord = new int[] {i,j};
                if (roll < 0.20) {
                    this.map[i][j] = new InaccessibleCell(coord); 
                }
                else if (roll < 0.50) {
                    this.map[i][j] = new MarketCell(coord);
                }
                else {
                    this.map[i][j] = new BattleCell(coord);
                }
            }
        }
    }
    
    public int[] getHeroSquadPosition() {
        return currPosition;
    }

    public Cell getCell(int[] position) {
        Cell result = map[position[0]][position[1]];
        switch (result.getType()) {
            case ("Market"): {
                return (MarketCell) result;
            }
            case ("Battle"): {
                return (BattleCell) result;
            }
            default: {
                return result;
            }
        }
    }

    public boolean moveSquad(int[] newPosition) {
        if (newPosition[0] < 0 || newPosition[1] < 0 || newPosition[0] >= map.length || newPosition[1] >= map[0].length) {
            System.out.println("This position is out of bounds.");
            return false;
        }
        if (getCell(newPosition).getType().equals("Inaccessible")) {
            System.out.println("This cell is inaccessible");
            return false;
        }
        ((AccessibleCell) this.map[currPosition[0]][currPosition[1]]).removeSquadHere();
        ((AccessibleCell) this.map[newPosition[0]][newPosition[1]]).setSquadHere();
        currPosition = newPosition;
        return true;
    }

}