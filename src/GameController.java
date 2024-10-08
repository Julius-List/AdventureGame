import java.util.Scanner;

public class GameController {
    Scanner scan = new Scanner(System.in);
    int input;
    boolean isFirstGame = true; // Laver en variabel som tjekker om det er første gang spilleren starter programmet.

public void start() {
    if (isFirstGame) {
    System.out.println("You wake up on the beach of a deserted island blablabla."); // startbesked.
    isFirstGame = false; // sætter isFirstGame til false efter, så man ikke får startbeskeden når man returnerer til start.

    }
    // Viser choice options.
    System.out.println("1: Stay on the beach.");
    System.out.println("2: Walk to the sea.");
    System.out.println("3: Walk to the jungle.");

    input = scan.nextInt();

    switch(input) { // switch
        case 1: // kalder instance af beach.
            Beach beachPath = new Beach();
            beachPath.enter();
            break;

        case 2: // kalder instance af sea.
            Sea seaPath = new Sea();
            seaPath.enter();
            break;

        case 3: // kalder instance af jungle.
            Jungle junglePath = new Jungle();
            junglePath.enter(this);
            break;

        // default case ved invalid choice der genkalder metoden igen (rekursion) så man kan vælge igen.
        default:
            System.out.println("You can only choose 1, 2, or 3.");
            start();
            break;

         }
    }
}
