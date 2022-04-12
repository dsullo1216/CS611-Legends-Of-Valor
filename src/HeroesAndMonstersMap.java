import java.io.IOException;
import java.util.Random;

public class HeroesAndMonstersMap implements Map {

    private Random rand = new Random();
    private Cell[][] map;
    private int[] currPosition;

    public HeroesAndMonstersMap() throws IOException {
        this.map = new Cell[8][8];
        this.map[0][0] = new BattleCell(new int[] {0,0});
        ((AccessibleCell) this.map[0][0]).setHeroSquadHere();
        currPosition = new int[] {0,0};
        initializeMap();
    }

    public HeroesAndMonstersMap(int mapSize) throws IOException {
        this.map = new Cell[mapSize][mapSize];
        initializeMap();
    }

    public HeroesAndMonstersMap(int mapLength, int mapWidth) throws IOException {
        this.map = new Cell[mapLength][mapWidth];
        initializeMap();
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

    public void initializeMap() throws IOException {
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

    public boolean moveSquad(int[] newPosition, int heroIndex) {
        if (newPosition[0] < 0 || newPosition[1] < 0 || newPosition[0] >= map.length || newPosition[1] >= map[0].length) {
            System.out.println("This position is out of bounds.");
            return false;
        }
        if (getCell(newPosition).getType().equals("Inaccessible")) {
            System.out.println("This cell is inaccessible");
            return false;
        }
        ((AccessibleCell) this.map[currPosition[0]][currPosition[1]]).removeHeroSquadHere();
        ((AccessibleCell) this.map[newPosition[0]][newPosition[1]]).setHeroSquadHere();
        currPosition = newPosition;
        return true;
    }
    

}
