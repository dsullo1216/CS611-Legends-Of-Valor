// Abstract class to represent the Cells on the map
public abstract class Cell {

    protected char symbol;
    protected boolean reachable;
    protected int[] position;
    protected String type;

    protected Cell(char symbol, boolean reachable, int[] position) {
        this.symbol = symbol;
        this.reachable = reachable;
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public char updateSymbol(char newSymbol) {
        this.symbol = newSymbol;
        return newSymbol;
    }

    public boolean isAccessible() {
        return reachable;
    }

    public int[] getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }
    
}