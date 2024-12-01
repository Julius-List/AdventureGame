import java.util.Scanner;

public class GameController {
    private BaseLocation currentLocation;
    private boolean isFirstGame = true;  // Flag to track the first time the game starts
    private final Scanner scanner = new Scanner(System.in);
    private final Player player; // Instance af Player

    // Constructor
    public GameController() {
        this.player = new Player (this); // Passing GameController to Player to add game over logic.
    }

    // First run message
    public void start() {
        if (isFirstGame) {
            System.out.println("You wake up on the beach of a deserted island. You feel disoriented but determined to survive.");
            isFirstGame = false;
        }

        showStartChoices();
    }

    // Show options from the starting point
    private void showStartChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Stay on the beach");
        System.out.println("2: Walk to the sea");
        System.out.println("3: Enter the jungle");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                setLocation(new Beach(this, player));  // Pass player og GameController to Beach
                break;
            case 2:
                setLocation(new Sea(this, player));    // Pass player og GameController to Sea
                break;
            case 3:
                setLocation(new Jungle(this, player)); // Pass player og GameController to Jungle
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                showStartChoices();
                break;
        }
    }

    // Method to update and move the player to a new location
    private void setLocation(BaseLocation location) {
        this.currentLocation = location;
        currentLocation.enter();  // Enter the new location
    }

    public void returnToStart() {
        showStartChoices();  // Only prompt the choices, no starting message
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

