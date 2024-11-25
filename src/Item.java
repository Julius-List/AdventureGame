import java.util.ArrayList;
import java.util.List;

public class Item {
    private final List<String> inventory;  /* List to store collected items. 'Final' keyword to ensure only 1
                                            inventory can exist at all times */

    public Item() {
        inventory = new ArrayList<>();  // Initialize as ArrayList
    }

    // Method to add an item to the inventory
    public void addItem(String item) {
        if (!inventory.contains(item)) {  // Avoid duplicates
            inventory.add(item);
            System.out.println("\u001B[34m" + item + " has been added to your inventory.\u001B[0m");
        } else {
            System.out.println("\u001B[34mYou already have " + item + " in your inventory.\u001B[0m");
        }
    }

    // Method to check if the inventory contains a specific item
    public boolean containsItem(String item) {
        return inventory.contains(item);
    }

    // Method to display the inventory
    public void showInventory() {
        System.out.println("\u001B[34mYour inventory contains: " + inventory + "\u001B[0m");
    }
}
