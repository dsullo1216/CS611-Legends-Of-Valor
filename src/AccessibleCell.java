// Abstract class to represent a cell where heroes can walk. Will be extended to become Battle Cells and Market Cells 
public abstract class AccessibleCell extends Cell {

    protected boolean isHeroSquadHere;
    protected boolean isMonsterSquadHere;
    
    protected AccessibleCell(char symbol, int[] position, String type) {
        super(symbol, true, position);
        this.isHeroSquadHere = false;
        this.isMonsterSquadHere = false;
    }

    @Override
    public char getSymbol() {
        if (isHeroSquadHere) {
            return 'H';
        }
        else if (isMonsterSquadHere) {
            return 'M';
        }
        return symbol;
    }

    public boolean setHeroSquadHere() {
        this.isHeroSquadHere = true;
        return this.isHeroSquadHere;
    }

    public boolean removeHeroSquadHere() {
        this.isHeroSquadHere = false;
        return this.isHeroSquadHere;
    }

    public boolean setMonsterSquadHere() {
        this.isMonsterSquadHere = true;
        return this.isHeroSquadHere;
    }

    public boolean removeMonsterSquadHere() {
        this.isMonsterSquadHere = false;
        return this.isHeroSquadHere;
    }
 
}