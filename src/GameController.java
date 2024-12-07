import java.util.Scanner;

public class GameController {
    private Location currentLocation;
    private boolean isFirstGame = true;
    private final Scanner scanner = new Scanner(System.in);
    private final XMLHandler xmlHandler; // XMLHandler er nu en dependency

    // Constructor modtager XMLHandler som parameter
    public GameController(XMLHandler xmlHandler) {
        this.xmlHandler = xmlHandler;
    }

    // Start spillet
    public void start() {
        if (isFirstGame) {
            System.out.println("You wake up on the beach of a deserted island. You feel disoriented but determined to survive.");
            isFirstGame = false;
        }
        loadLocation("Start"); // Start med scenen med id "Start"
    }

    // Metode til at indlæse en lokation baseret på dens ID
    public void loadLocation(String locationId) {
        Location location = xmlHandler.getLocationById(locationId); // Hent lokationen fra XML
        if (location != null) {
            currentLocation = location;
            displayLocation(); // Vis lokationen for spilleren
        } else {
            System.out.println("Location not found: " + locationId);
            gameOver(); // Slut spillet, hvis lokationen ikke findes
        }
    }

    // Vis den aktuelle lokation og valgmuligheder for spilleren
    public void displayLocation() {
        System.out.println(currentLocation.getDescription()); // Beskrivelse af lokationen

        // Vis valgmuligheder
        if (currentLocation.getOptions().isEmpty()) {
            System.out.println("No options available. Game over!");
            gameOver();
        } else {
            for (int i = 0; i < currentLocation.getOptions().size(); i++) {
                System.out.println((i + 1) + ": " + currentLocation.getOptions().get(i).getText());
            }

            // Håndter spillerens valg
            handlePlayerChoice();
        }
    }

    // Håndter spillerens valg
    public void handlePlayerChoice() {
        int choice = scanner.nextInt();
        if (choice < 1 || choice > currentLocation.getOptions().size()) {
            printInvalidChoiceMessage(currentLocation.getOptions().size());
            handlePlayerChoice();
        } else {
            String nextLocationId = currentLocation.getOptions().get(choice - 1).getNextLocationId();
            loadLocation(nextLocationId); // Gå til næste lokation
        }
    }

    // Udskriv en fejlmeddelelse ved ugyldigt valg
    public void printInvalidChoiceMessage(int maxOptions) {
        System.out.println("Invalid choice. Please select a number between 1 and " + maxOptions + ".");
    }

    // Slut spillet, hvis spilleren dør
    public void gameOver() {
        System.out.println("\u001B[31mG A M E  O V E R\u001B[0m" +
                "\nYou didn't manage to survive the treacherous island. Better luck next time!");
        System.exit(0);
    }

    // Slut spillet, hvis spilleren vinder
    public void gameWon() {
        System.out.println("\u001B[32mCongratulations, survivor. You escaped the island!\u001B[0m");
        System.exit(0);
    }
}