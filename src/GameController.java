import java.util.Scanner;

public class GameController {
    private BaseLocation currentLocation;
    private boolean isFirstGame = true;  // Flag to track the first time the game starts
    private final Scanner scanner = new Scanner(System.in);
    private final Player player; // Instance af Player
    // Constants for the name of each unique location
    private static final String BEACH_NAME = "beach";
    private static final String SEA_NAME = "sea";
    private static final String JUNGLE_NAME = "jungle";

    // Constructor
    public GameController() {
        this.player = new Player (this); // Passing GameController to Player to add game over logic.
    }

    // Show start message and start choices when the game is initialized
    public void start() {
        if (isFirstGame) {
            System.out.println("You wake up on the beach of a deserted island. You feel disoriented but determined to survive.");
            isFirstGame = false;
        }

        showStartChoices();
    }

    private void showStartChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Stay on the beach");
        System.out.println("2: Walk to the sea");
        System.out.println("3: Enter the jungle");

        int choice = scanner.nextInt();

        // Creates a new location instance with its name, gameController and player
        switch (choice) {
            case 1:
                moveToLocation(new Beach(BEACH_NAME,this, player));
                break;
            case 2:
                moveToLocation(new Sea(SEA_NAME,this, player));
                break;
            case 3:
                moveToLocation(new Jungle(JUNGLE_NAME,this, player));
                break;
            default:
                this.printInvalidChoiceMessage(3);
                showStartChoices();
                break;
        }
    }

    // Method to prompt the player with a message to choose a valid choice option
    public void printInvalidChoiceMessage(int maxOptions) {
        if (maxOptions == 1) {
            System.out.println("Invalid choice. Please select the only available option: 1.");
        } else {

            System.out.println("Invalid choice. Please select a number between 1 and " + maxOptions + ".");
        }
    }

    // Method to update and move the player to a new location
    private void moveToLocation(BaseLocation location) {
        this.currentLocation = location;
        currentLocation.enter();  // Enter the new location
    }

    // Method to return the player to start
    public void returnToStart() {
        showStartChoices();  // Shows the start choices if the player returns to start
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