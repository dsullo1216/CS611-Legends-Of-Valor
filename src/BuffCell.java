public abstract class BuffCell extends AccessibleCell {

    protected String buffedStat;

    protected BuffCell(char symbol, int[] position, String buffedStat) {
        super(symbol, position, "");
    }
    
}