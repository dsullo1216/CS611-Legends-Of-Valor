// Subclass of the Cell class that represents cells where the user cannot walk
public class InaccessibleCell extends Cell {
    
    public InaccessibleCell(int[] position) {
        super('X', false, position);
        type = "Inaccessible";
    }

}