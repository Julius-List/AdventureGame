import java.util.Scanner;
import java.util.Random;

// Initializes protected variables for subclasses
public abstract class BaseLocation {
    protected String locationName;
    protected Scanner scanner = new Scanner(System.in);
    protected Random random = new Random();

    public BaseLocation(String locationName) {
        this.locationName = locationName;
    }

    // Concrete method to show unique entry messages in subclasses
    public void enter() {
        System.out.println(getEntryMessage());
        handleChoices();
    }

    // Abstract method for handling choices in subclasses
    public abstract void handleChoices();

    protected abstract String getEntryMessage();
}