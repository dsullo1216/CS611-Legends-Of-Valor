// Abstract class that represents a collection of Heroes. Will be used for player's party as well as hoardes of mobs
public abstract class Squad {
    
    protected Entity[] party;

    public String toString() {
        String result = "No / " + party[0].description() + '\n';;
        for (int i = 0; i < party.length; i++) {
            result += Integer.toString(i+1) + ". " + party[i].toString() + '\n';
        }
        return result;
    }

    public boolean addEntity(Entity newEntity) {
        int i = 0;
        while (party[i] != null && i < party.length) {
            i++;
        }
        if (i >= party.length) {
            return false;
        }
        party[i] = newEntity;
        return true;
    }

    public boolean removeEntity(Entity entity) {
        for (int i = 0; i < party.length; i++) {
            if (party[i].equals(entity)) {
                party[i] = null;
                return true;
            }
        }
        return false;
    }

    public int findEntity(Entity entity) {
        for (int i = 0; i < party.length; i++) {
            if (party[i] == null) {
                return -1;
            }
            if (party[i].equals(entity)) {
                return i;
            }
        }
        return -1;
    }

    public Entity getEntityAt(int index) {
        return party[index];
    }

    public int nextAlive() {
        for (int i = 0; i < party.length; i++) {
            if (party[i] != null && party[i].getHP() > 0) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEliminated() {
        for (int i = 0; i < party.length; i++) {
            if (party[i].getHP() > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < party.length; i++) {
            if (party[i] == null) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return party.length;
    }

}