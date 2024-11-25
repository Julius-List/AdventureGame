import java.util.Scanner;
import java.util.Random;

// Initializes protected variables for the subclasses
public abstract class BaseLocation {
    protected String locationName;
    protected Item playerItems;
    protected Scanner scanner = new Scanner(System.in);
    protected Random random = new Random();

    public BaseLocation(String locationName, Item playerItems) {
        this.locationName = locationName;
        this.playerItems = playerItems;
    }

    // Abstract method for handling choices in each subclass
    public abstract void handleChoices();

    // Abstract method for entry behavior in each subclass
    public abstract void enter();
}
