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
    
    // Will not get overriden by getSymbol method of the child Cell classes
    // For LOV, will prevent a cell from being displayed with the Hero or Monster symbol
    public char getIcon() {
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