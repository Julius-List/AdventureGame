import java.util.Scanner;

public class GameController {
    private static final Scanner scan = new Scanner(System.in);
    private static XMLHandler xmlHandler;
    private static boolean isFirstGame = true;

    // Constructor til GameController, der tager en XMLHandler
    public GameController(XMLHandler xmlHandler) {
        GameController.xmlHandler = xmlHandler;
    }

    public static void start() {
        String currentSceneId = "Start"; // Start med scenen "Start"

        while (true) {
            Scene currentScene = xmlHandler.getSceneById(currentSceneId);
            if (currentScene == null) {
                System.out.println("Fejl: Kunne ikke finde scenen.");
                break;
            }

            // Viser tekst for første besøg eller prompt
            if (isFirstGame && currentScene.getFirstVisit() != null) {
                System.out.println(currentScene.getFirstVisit());
                isFirstGame = false;
            } else if (currentScene.getPrompt() != null) {
                System.out.println(currentScene.getPrompt());
            }

            // Viser valgmuligheder
            if (currentScene.getOptions() != null && !currentScene.getOptions().isEmpty()) {
                for (int i = 0; i < currentScene.getOptions().size(); i++) {
                    System.out.println((i + 1) + ": " + currentScene.getOptions().get(i).getText());
                }

                int input = scan.nextInt();

                if (input < 1 || input > currentScene.getOptions().size()) {
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    continue;
                }

                // Skift til næste scene baseret på spillerens valg
                String nextSceneId = currentScene.getOptions().get(input - 1).getNextScene();

                // Hvis spilleren vælger at gå til junglen, så opretter vi en Jungle instans og kalder dens enter() metode
                if (nextSceneId.equals("Jungle")) {
                    Jungle jungleAdventure = new Jungle(xmlHandler);
                    jungleAdventure.enter();
                } else {
                    currentSceneId = nextSceneId;
                }
            } else {
                System.out.println("Ingen valgmuligheder tilgængelige, spillet afsluttes.");
                break;
            }
        }
    }
}
