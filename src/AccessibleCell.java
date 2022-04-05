// Abstract class to represent a cell where heroes can walk. Will be extended to become Battle Cells and Market Cells 
public abstract class AccessibleCell extends Cell {

    protected boolean isHeroSquadHere;
    
    protected AccessibleCell(char symbol, int[] position) {
        super(symbol, true, position);
        this.isHeroSquadHere = false;
    }

    @Override
    public char getSymbol() {
        if (isHeroSquadHere) {
            return 'H';
        }
        return symbol;
    }

    public boolean setSquadHere() {
        this.isHeroSquadHere = true;
        return this.isHeroSquadHere;
    }

    public boolean removeSquadHere() {
        this.isHeroSquadHere = false;
        return this.isHeroSquadHere;
    }
 
}