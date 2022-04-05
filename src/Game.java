import java.io.IOException;
import java.util.Scanner;

// Abstract class to represent any Game using Scanner
public abstract class Game {
 
    protected Map map;

    public abstract void launchGame(Scanner sc) throws IOException;

    public abstract void playGame(Scanner sc) throws IOException;

}