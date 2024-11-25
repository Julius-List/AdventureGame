import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class GameController {
    private static XMLHandler xmlHandler;
    private static String currentSceneId = "Start"; // Starten af spillet
    private static Scanner scanner = new Scanner(in);
    private static Random random = new Random();
    private static boolean isFirstGame = true; // For at kontrollere, om det er første gang, scenen spilles

    // Constructor til GameController, der tager en XMLHandler som argument
    public GameController(XMLHandler xmlHandler) {
        this.xmlHandler = xmlHandler;
    }

    // Metode til at starte spillet
    public static void start() {
        while (true) {
            Scene currentScene = xmlHandler.getSceneById(currentSceneId);
            if (currentScene == null) {
                out.println("Fejl: Kunne ikke finde scenen.");
                break;
            }

            // Viser "firstVisit" prompten for første besøg, hvis det er første gang spilleren besøger scenen
            if (isFirstGame && currentScene.getFirstVisit() != null) {
                out.println(currentScene.getFirstVisit());
                isFirstGame = false; // Efter første gang sættes dette til false
                out.println("\n");
            } else if (currentScene.getPrompt() != null) {
                out.println(currentScene.getPrompt());
                out.println("\n");
            }

            // Håndterer tilfældige begivenheder, hvis de findes, men kun én gang per scene
            if (currentScene.getRandomEvents() != null && !currentScene.getRandomEvents().isEmpty()) {
                handleRandomEvent(currentScene.getRandomEvents());
                out.println("\n");  // Tilføjer to ekstra linjer for at adskille begivenhedsteksten fra valgmulighederne
            }

            // Viser de tilgængelige valgmuligheder for denne scene
            if (currentScene.getOptions() != null && !currentScene.getOptions().isEmpty()) {
                out.println("What do you choose?");  // Brug denne linje som introduktion til valgmulighederne

                // Viser valgmulighederne med ekstra indryk
                for (int i = 0; i < currentScene.getOptions().size(); i++) {
                    out.println("\t" + (i + 1) + ": " + currentScene.getOptions().get(i).getText());
                }

                out.println("\n");  // Tilføjer to ekstra linjer efter valgmulighederne for at adskille fra brugerens input

                // Får spillerens valg
                int choice = scanner.nextInt();
                if (choice < 1 || choice > currentScene.getOptions().size()) {
                    out.println("Ugyldigt valg. Prøv igen.");
                    continue;
                }

                // Opdaterer den aktuelle scene baseret på spillerens valg
                Option selectedOption = currentScene.getOptions().get(choice - 1);
                currentSceneId = selectedOption.getNextScene();

                // Reset 'isFirstGame' når spilleren skifter til en ny scene
                isFirstGame = true;
            } else {
                // Hvis der ikke er nogen valgmuligheder eller tilfældige begivenheder, afslut scenen
                out.println("Scenen er afsluttet.");
                break;
            }
        }
    }

    // Metode til at håndtere tilfældige begivenheder
    private static void handleRandomEvent(List<RandomEvent> randomEvents) {
        if (randomEvents == null || randomEvents.isEmpty()) {
            return; // Ingen tilfældige begivenheder at håndtere
        }

        int roll = random.nextInt(100);  // Genererer et tilfældigt tal mellem 0 og 99

        int cumulativeProbability = 0;
        for (RandomEvent event : randomEvents) {
            cumulativeProbability += event.getProbability();
            if (roll < cumulativeProbability) {
                // Udskriver den valgte tilfældige begivenhed og stopper derefter
                out.println(event.getEventText());
                return;  // Stopper efter at have udskrevet én tilfældig begivenhed
            }
        }

        // Hvis ingen begivenhed blev udløst (f.eks. hvis kumulerede sandsynligheder ikke er 100%)
        out.println("Ingenting skete.");
    }
}
