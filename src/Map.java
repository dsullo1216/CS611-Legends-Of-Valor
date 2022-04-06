import java.io.IOException;

// Collection class that holds instances of Cells to represent the map. Includes methods to randomize the map.
public interface Map {

    public void printMap();

    public void randomizeMap() throws IOException;
    
    public int[] getHeroSquadPosition();

    public Cell getCell(int[] position);
    
    public boolean moveSquad(int[] newPosition);

}