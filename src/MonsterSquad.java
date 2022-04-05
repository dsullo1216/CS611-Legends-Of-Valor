import java.io.IOException;
import java.util.*;

// Represents a hoarde of monsters to be fought during a battle
public class MonsterSquad extends Squad{
    
    public MonsterSquad(int size) {
        this.party = new Monster[size];
    }
    
    public void randomizeMonsterSquad() throws IOException {
        Monster[] monsterList = ReadFiles.ListOfMonsters();
        Collections.shuffle(Arrays.asList(monsterList));
        for (int i = 0; i < party.length; i++) {
            addEntity(monsterList[i]);
        }
    }
}