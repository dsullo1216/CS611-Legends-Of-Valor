// Subclass of AccessibleCell that represents a cell where the user can stand in LOV
public class PlainCell extends AccessibleCell {

    public PlainCell(int[] position) {
        super('P', position, "Plain");
        type = "Plain";
    }
    
}