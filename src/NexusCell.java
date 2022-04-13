import java.io.IOException;

// Subclass of MarketCell that represents a cell where the user can go into the market and teleport back to in LOV
public class NexusCell extends MarketCell {

    public NexusCell(int[] position) throws IOException {
        super(position);
        symbol = 'N';
        type = "Nexus";
    }
    
}