import java.io.IOException;
import java.util.*;

public class LegendsOfValorMap implements Map {

    private Random rand = new Random();
    private Cell[][] map;
    private HashMap<Hero, int[]> currPositions;

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

    public int[] getHeroPosition(Hero hero) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cell getCell(int[] position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean moveSquad(int[] newPosition) {
        // TODO Auto-generated method stub
        return false;
    }
    
}