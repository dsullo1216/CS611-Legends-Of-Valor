// Subclass of BuffCell that represents a cell where the user will receive a dexterity boost
public class BushCell extends BuffCell{

    public BushCell(int[] position) {
        super('B', position, "dexterity");
        type = "Bush";
    }
    
}