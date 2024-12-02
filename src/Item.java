// Class to manage predefined items
public class Item {
    private final String name;

    // Instantiates items as objects
    public static final Item LIGHTER = new Item("Lighter");
    public static final Item FISHING_HAT = new Item("Fishing Hat");
    public static final Item PRETTY_ROCK = new Item("Pretty Rock");
    public static final Item PRETTY_LEAF = new Item("Pretty Leaf");
    public static final Item STRANGE_MAP = new Item("Strange Map");
    public static final Item NECKLACE = new Item("Necklace");
    public static final Item Wood_and_Palm_Leaves = new Item("Wood and Palm Leaves");

    // Accepts a name and assigns it to the name field of an item
    public Item (String name) {
    this.name = name;
    }

    // Getter to access the item's name
    public String getName() {
        return name;
    }

    // Overrides the called item so it displays correctly in prologue
    @Override
    public String toString() {
        return name;
    }
}