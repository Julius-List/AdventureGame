import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jungle {
    private XMLHandler xmlHandler;
    private String currentSceneId = "Jungle";
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    // Constructor
    public Jungle(XMLHandler xmlHandler) {
        this.xmlHandler = xmlHandler;
    }

    // Start jungle adventure
    public void enter() {
        while (true) {
            Scene currentScene = xmlHandler.getSceneById(currentSceneId);
            if (currentScene == null) {
                System.out.println("Fejl: Kunne ikke finde scenen.");
                break;
            }

            // Display prompt for the current scene
            if (currentScene.getPrompt() != null) {
                System.out.println(currentScene.getPrompt());
            }

            // Display available options for the current scene
            if (currentScene.getOptions() != null && !currentScene.getOptions().isEmpty()) {
                for (int i = 0; i < currentScene.getOptions().size(); i++) {
                    System.out.println((i + 1) + ": " + currentScene.getOptions().get(i).getText());
                }

                // Get player's choice
                int choice = scanner.nextInt();
                if (choice < 1 || choice > currentScene.getOptions().size()) {
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    continue;
                }

                // Get the selected option
                Option selectedOption = currentScene.getOptions().get(choice - 1);

                // Check if the selected option contains random events
                if (selectedOption.getRandomEvents() != null && !selectedOption.getRandomEvents().isEmpty()) {
                    handleRandomEvent(selectedOption.getRandomEvents());
                }

                // Update the current scene based on the selected option
                currentSceneId = selectedOption.getNextScene();
            } else {
                System.out.println("Ingen valgmuligheder tilgængelige, spillet afsluttes.");
                break;
            }
        }
    }

    private void handleRandomEvent(List<RandomEvent> randomEvents) {
        int cumulativeProbability = 0;
        int roll = random.nextInt(100);  // Get a number between 0 and 99 to simulate percentage

        for (RandomEvent event : randomEvents) {
            cumulativeProbability += event.getProbability();
            if (roll < cumulativeProbability) {
                System.out.println(event.getEventText());
                return;  // Stop after the first event that matches the probability roll
            }
        }
    }
}
