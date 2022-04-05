// Subclass of AccessibleCell that represents commonspaces where enemies can be encountered
public class BattleCell extends AccessibleCell {

    public BattleCell(int[] position) {
        super('_', position);
        type = "Battle";
    }

}