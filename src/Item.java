import java.util.ArrayList;
import java.util.List;

public class Item {
    private final List<String> inventory;  /* List to store collected items. 'Final' keyword to ensure only 1
                                            inventory can exist at all times */

    public Item() {
        inventory = new ArrayList<>();  // Initialize the inventory list
    }

    // Method to add an item to the inventory
    public void addItem(String item) {
        if (!inventory.contains(item)) {  // Avoid duplicates if needed
            inventory.add(item);
            System.out.println(item + " has been added to your inventory.");
        } else {
            System.out.println("You already have " + item + " in your inventory.");
        }
    }

    // Method to display the inventory
    public void showInventory() {
        System.out.println("Your inventory contains: " + inventory);
    }
}
