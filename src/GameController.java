import java.util.Scanner;

public class GameController {
    private Scene currentScene;
    private boolean isFirstGame = true;
    private final Scanner scanner = new Scanner(System.in);
    private final XMLHandler xmlHandler;

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
        loadScene("Start"); // Start med scenen med ID "Start"
    }

    // Metode til at indlæse en scene baseret på dens ID
    public void loadScene(String sceneId) {
        Scene scene = xmlHandler.getSceneById(sceneId); // Hent scenen fra XML
        if (scene != null) {
            currentScene = scene;
            displayScene(); // Vis scenen for spilleren
        } else {
            System.out.println("Scene not found: " + sceneId);
            gameOver(); // Slut spillet, hvis scenen ikke findes
        }
    }

    // Vis den aktuelle scene og valgmuligheder for spilleren
    public void displayScene() {
        // Vis første besøgstekst eller prompt
        if (currentScene.getFirstVisit() != null && isFirstGame) {
            System.out.println(currentScene.getFirstVisit());
        } else {
            System.out.println(currentScene.getPrompt());
        }

        // Vis valgmuligheder
        if (currentScene.getOptions().isEmpty()) {
            System.out.println("No options available. Game over!");
            gameOver();
        } else {
            for (int i = 0; i < currentScene.getOptions().size(); i++) {
                System.out.println((i + 1) + ": " + currentScene.getOptions().get(i).getText());
            }

            // Håndter spillerens valg
            handlePlayerChoice();
        }
    }

    // Håndter spillerens valg
    public void handlePlayerChoice() {
        int choice = scanner.nextInt();
        if (choice < 1 || choice > currentScene.getOptions().size()) {
            printInvalidChoiceMessage(currentScene.getOptions().size());
            handlePlayerChoice();
        } else {
            String nextSceneId = currentScene.getOptions().get(choice - 1).getNextSceneId();
            loadScene(nextSceneId); // Gå til næste scene
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
