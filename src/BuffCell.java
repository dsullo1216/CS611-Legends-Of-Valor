public abstract class BuffCell extends AccessibleCell {

    protected String buffedStat;

    protected BuffCell(char symbol, int[] position, String buffedStat) {
        super(symbol, position, "");
        this.buffedStat = buffedStat;
    }
    
    public void boostStat(Hero heroToBoost) {
    	switch (this.buffedStat) {
        	// Bush Cell
    		case ("dexterity"): {
    			heroToBoost.updateTempBoost( (int) (heroToBoost.getDexterity() * 0.10) );
    			heroToBoost.updateDexterity( (int) (heroToBoost.getDexterity() * 1.10) );
    			break;
        	}
    		// Cave Cell
    		case ("agility"): {
    			heroToBoost.updateTempBoost( (int) (heroToBoost.getAgility() * 0.10) );
    			heroToBoost.updateAgility( (int) (heroToBoost.getAgility() * 1.10) );
    			break;
    		}
    		// Koulou Cell
    		case ("strength"): {
    			heroToBoost.updateTempBoost( (int) (heroToBoost.getStrength() * 0.10) );
    			heroToBoost.updateStrength( (int) (heroToBoost.getStrength() * 1.10) );
    			break;
    		}
    		default: {
    			return;
    		}
        }
    }
    
    public void revertBoostedStat(Hero heroToBoost) {
    	switch (this.buffedStat) {
    	// Bush Cell
		case ("dexterity"): {
			heroToBoost.updateDexterity(heroToBoost.getDexterity() - heroToBoost.getTempBoost());
			break;
    	}
		// Cave Cell
		case ("agility"): {
			heroToBoost.updateAgility(heroToBoost.getAgility() - heroToBoost.getTempBoost());
			break;
		}
		// Koulou Cell
		case ("strength"): {
			heroToBoost.updateStrength(heroToBoost.getStrength() - heroToBoost.getTempBoost());
			break;
		}
		default: {
			return;
		}
    }
    }
    
}