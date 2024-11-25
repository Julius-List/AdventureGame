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

            // Display prompt if it exists
            if (currentScene.getPrompt() != null) {
                System.out.println(currentScene.getPrompt());
            }

            // Handle random events if present in this scene (like in GrabTheFruitRandom)
            if (currentScene.getRandomEvents() != null && !currentScene.getRandomEvents().isEmpty()) {
                handleRandomEvent(currentScene.getRandomEvents());
            }
            System.out.println("Handling random event for scene: " + currentScene.getId());


            // Display available options if they exist
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

                Option selectedOption = currentScene.getOptions().get(choice - 1);
                currentSceneId = selectedOption.getNextScene(); // Move to the next scene
            } else if (currentScene.getOptions() == null && currentScene.getRandomEvents() == null) {
                // If there are no options and no random events, break (scene is finished)
                System.out.println("Scenen er afsluttet.");
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
