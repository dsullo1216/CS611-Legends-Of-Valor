import java.io.IOException;

// Collection class that holds instances of Cells to represent the map. Includes methods to randomize the map.
public interface Map {

    public void printMap();

    public void initializeMap() throws IOException;
    

    public Cell getCell(int[] position);
    
    public boolean moveSquad(int[] newPosition);

}