import java.util.Scanner;

public class GameController {
    private BaseLocation currentLocation;
    private boolean isFirstGame = true;  // Boolean flag to track the first time the game starts
    private final Scanner scanner = new Scanner(System.in); // Takes input from the players keyboard

    // Constants for each location name (used to initialize and identify locations)
    private static final String BEACH_NAME = "beach";
    private static final String SEA_NAME = "sea";
    private static final String JUNGLE_NAME = "jungle";

    // Predefined instances of each location
    private final BaseLocation beach;
    private final BaseLocation sea;
    private final BaseLocation jungle;

    // Constructor to initialize the game components
    public GameController() {
        // Player instance to manage health and inventory
        Player player = new Player(this); // Passes GameController to Player
        this.beach = new Beach(BEACH_NAME, this, player);
        this.sea = new Sea(SEA_NAME, this, player);
        this.jungle = new Jungle(JUNGLE_NAME, this, player);
        this.currentLocation = beach; // Start the game at the beach
    }

    // Show start message and start choices when the game is initialized
    public void start() {
        if (isFirstGame) {
            System.out.println("You wake up on the beach of a deserted island. You feel disoriented but determined to survive.");
            isFirstGame = false;
        }
        showStartChoices();
    }

    public void showStartChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Stay on the beach");
        System.out.println("2: Walk to the sea");
        System.out.println("3: Enter the jungle");

        int choice = scanner.nextInt();

        // Creates a new location instance
        switch (choice) {
            case 1:
                moveToLocation(beach);
                break;
            case 2:
                moveToLocation(sea);
                break;
            case 3:
                moveToLocation(jungle);
                break;
            default:
                this.printInvalidChoiceMessage(3);
                showStartChoices();
                break;
        }
    }

    // Method to move the player to a new location
    public void moveToLocation(BaseLocation location) {
        this.currentLocation = location;
        currentLocation.enter();  // Enter the new location
    }

    // Getter for beach
    public BaseLocation getBeachLocation() {
        return beach;
    }


    // Method to return the player to the starting location
    public void returnToStart() {
        moveToLocation(beach);
    }

    // Method to print a message if the player chooses an invalid choice
    public void printInvalidChoiceMessage(int maxOptions) {
        if (maxOptions == 1) {
            System.out.println("Invalid choice. Please select the only available option: 1.");
        } else {
            System.out.println("Invalid choice. Please select a number between 1 and " + maxOptions + ".");
        }
    }

    // Method to end the game if the player dies
    public void gameOver() {
        System.out.println("\u001B[31mG A M E  O V E R\u001B[0m" +
                "\nYou didn't manage to survive the treacherous island. Better luck next time!");
        System.exit(0); // Terminates the process
    }

    // Method to end the game if the player wins
    public void gameWon() {
        System.out.println("\u001B[32mCongratulations, survivor. You escaped the island!\u001B[0m");
        System.exit(0); // Terminates the process
    }
}