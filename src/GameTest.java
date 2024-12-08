public class GameTest {
    public static void main(String[] args) {
        // Debugging af XMLHandler
        System.out.println("Initialising XMLHandler...");
        XMLHandler xmlHandler = new XMLHandler("src/AdventureStory.xml");

        // Test hentning af en scene
        System.out.println("Testing getSceneById for ID 'Start'...");
        Scene startScene = xmlHandler.getSceneById("Start");

        if (startScene != null) {
            // Debug Scene data
            System.out.println("\nScene successfully loaded:");
            System.out.println("ID: " + startScene.getId());
            System.out.println("First Visit: " + startScene.getFirstVisit());
            System.out.println("Second Visit: " + startScene.getSecondVisit());
            System.out.println("Prompt: " + startScene.getPrompt());

            // Debug Options
            System.out.println("\nOptions:");
            for (Option option : startScene.getOptions()) {
                System.out.println(" - " + option.getText() + " -> " + option.getNextSceneId());
            }

            // Debug Random Events
            System.out.println("\nRandom Events:");
            for (RandomEvent event : startScene.getRandomEvents()) {
                System.out.println(" - Probability: " + event.getProbability() + "%");
                System.out.println("   Event Text: " + event.getEventText());
            }
        } else {
            System.out.println("Failed to load scene with ID 'Start'.");
        }
    }
}