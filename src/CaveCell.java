// Subclass of BuffCell that represents a cell where the user will receive an agility boost
public class CaveCell extends BuffCell {
    
    public CaveCell(int[] position) {
        super('C', position, "agility");
        type = "Cave";
    }

}