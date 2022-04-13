// Subclass of BuffCell that represents a cell where the user will receive a strenght boost
public class KoulouCell extends BuffCell {

    public KoulouCell(int[] position) {
        super('K', position, "strength");
        type = "Koulou";
    }
    
}