import java.util.Scanner;

public class Jungle {
    private XMLHandler xmlHandler;  // Reference til XMLHandler for at hente scener
    private String currentSceneId = "Jungle"; // Start ID for junglescenen
    private Scanner scan = new Scanner(System.in);

    // Constructor til Jungle klassen
    public Jungle(XMLHandler xmlHandler) {
        this.xmlHandler = xmlHandler;
    }

    // Denne metode bruges til at starte jungleeventyret
    public void enter() {
        while (true) {
            Scene currentScene = xmlHandler.getSceneById(currentSceneId);
            if (currentScene == null) {
                System.out.println("Fejl: Kunne ikke finde scenen.");
                break;
            }

            // Udskriv scenetekst (prompt eller andet)
            if (currentScene.getPrompt() != null) {
                System.out.println(currentScene.getPrompt());
            }

            // Udskriv valgmuligheder
            if (currentScene.getOptions() != null && !currentScene.getOptions().isEmpty()) {
                for (int i = 0; i < currentScene.getOptions().size(); i++) {
                    System.out.println((i + 1) + ": " + currentScene.getOptions().get(i).getText());
                }

                int input = scan.nextInt();

                if (input < 1 || input > currentScene.getOptions().size()) {
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    continue;
                }

                // Find den næste scene baseret på brugerens valg
                String nextSceneId = currentScene.getOptions().get(input - 1).getNextScene();

                // Hvis spilleren beslutter sig for at gå tilbage til stranden, bruger vi GameController
                if (nextSceneId.equals("Start")) {
                    System.out.println("Du vender tilbage til stranden.");
                    GameController.start();
                    break;
                } else {
                    // Opdater til næste scene i junglen
                    currentSceneId = nextSceneId;
                }
            } else {
                System.out.println("Ingen valgmuligheder tilgængelige, jungleeventyret afsluttes.");
                break;
            }
        }
    }
}
