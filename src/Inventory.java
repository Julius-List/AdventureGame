import java.util.ArrayList;
import java.util.List;

// Class to manage a collection of item objects
public class Inventory {
    final List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Method to add item objects to the inventory array list
    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println("\u001B[34m" + item.getName() + " has been added to your inventory.\u001B[0m");
        } else {
            System.out.println("\u001B[34m" + item.getName() + " is already in the inventory.\u001B[0m");
        }
    }

    // Method to check if the player already has an item
    public boolean containsItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}