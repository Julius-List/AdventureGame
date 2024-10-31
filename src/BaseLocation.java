/*Formålet med BaseLocation er at den som abstract class indeholder den gængse struktur og funktionalitet som
de tre main locations nedarver fra.*/

import java.util.Scanner;
import java.util.Random;

public abstract class BaseLocation {
    protected String locationName;
    protected Item playerItems;
    protected Scanner scanner = new Scanner(System.in);
    protected Random random = new Random();

    public BaseLocation(String locationName, Item playerItems) {
        this.locationName = locationName;
        this.playerItems = playerItems;
    }

    // Abstract method for handling choices in each specific location
    public abstract void handleChoices();
    // Abstract method for entry behavior (unique to each location)
    public abstract void enter();
}
