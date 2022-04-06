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
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean moveSquad(int[] newPosition, int heroIndex) {
        // TODO Auto-generated method stub
        return false;
    }
    
}