import java.io.IOException;

public class NexusCell extends MarketCell {

    public NexusCell(int[] position) throws IOException {
        super(position);
        symbol = 'N';
        type = "Nexus";
    }
    
}