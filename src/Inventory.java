import java.io.IOException;
import java.util.*;

// Class that represents either a hero's inventory or the inventory of a shop
public class Inventory {
    
    private Item[] items;

    public Inventory(int length) {
        this.items = new Item[length];
    }

    public void randomizeInventory() throws IOException {
        Item[] itemList = ReadFiles.ListOfItems();
        Collections.shuffle(Arrays.asList(itemList));
        for (int i = 0; i < items.length; i++) {
            addItem(itemList[i]);
        }
        
    }

    public boolean addItem(Item item) {
        int i = 0;
        while (items[i] != null && i < items.length)  {
            i++;
        }
        if (i >= items.length) {
            return false;
        }
        items[i] = item;
        return true;
    }

    public boolean removeItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == item) {
                items[i] = null;
                return true;
            }
        }
        return false;
    }

    public int findItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public Item getItemAt(int index) {
        return items[index];
    }

    public int size() {
        return items.length;
    }

    public boolean isFull() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                return false;
            }
        }
        return true;
    }

}