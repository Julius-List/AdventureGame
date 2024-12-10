import java.util.Scanner;
import java.util.Random;

// Initializes protected variables for subclasses
public abstract class BaseLocation {
    protected String locationName;
    protected Scanner scanner = new Scanner(System.in);
    protected Random random = new Random();
    protected GameController gameController;
    protected Player player;

    // Constructor
    public BaseLocation(String locationName, GameController gameController, Player player) {
        this.locationName = locationName;
        this.gameController = gameController;
        this.player = player;
    }

    // Concrete method to print unique entry messages and show first choices in subclasses
    public void enter() {
        System.out.println(getEntryMessage());
        handleChoices();
    }

    // Abstract method for showing unique entry messages
    protected abstract String getEntryMessage();

    // Abstract method for handling choices in subclasses
    public abstract void handleChoices();
}