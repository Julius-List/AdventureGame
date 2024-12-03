// Class to manage predefined items
public class Item {
    private final String name;

    // Instantiates items as objects
    public static final Item LIGHTER = new Item("Lighter");
    public static final Item FISHING_HAT = new Item("Fishing Hat");
    public static final Item FISHING_ROD = new Item("Fishing Rod");
    public static final Item PRETTY_ROCK = new Item("Pretty Rock");
    public static final Item PRETTY_LEAF = new Item("Pretty Leaf");
    public static final Item STRANGE_MAP = new Item("Strange Map");
    public static final Item NECKLACE = new Item("Necklace");
    public static final Item WOOD_AND_PALM_LEAVES = new Item("Wood and Palm Leaves");
    public static final Item STOLEN_JOURNAL = new Item("Stolen Journal");

    // Accepts a name and assigns it to the name field of an item
    public Item (String name) {
    this.name = name;
    }

    // Getter to access the item's name
    public String getName() {
        return name;
    }

    // Overrides the called item so it displays correctly in the log
    @Override
    public String toString() {
        return name;
    }
}