import java.util.Scanner;

public class Jungle {
    Scanner scan = new Scanner(System.in);
    int input;

    public void enter(GameController gameController) { // Så vi kan bruge GameController instance
        System.out.println("You walk towards the jungle...");
        firstChoice(gameController); // kalder firstChoice med gameController som parameter.

    }

    private void firstChoice(GameController gameController) { // bruger gameController så vi kan calle start i option 1.
        System.out.println("As you step into the jungle, you feel like you are being watched.");
        System.out.println("1: Do you go back?");
        System.out.println("2: Do you turn right?");
        System.out.println("3: Do you turn left?");

        int input = scan.nextInt();


        switch (input) {
            case 1:
                System.out.println("You can't shake the feeling that something is wrong. You head back to the beach.");
                gameController.start(); // Returnerer til start.
                break;

            case 2:
                System.out.println("You decide to take the path to your right. You stumble upon a tree bearing fruits.");
                break;

            case 3:
                System.out.println("As you turn left, you trip over a branch and tumble down a mudslide. (You find a lighter) How convenient! Walk back to the beach or continue?");
                break;

            // default case ved invalid choice der genkalder metoden igen (rekursion) så man kan vælge igen.
            default:
                System.out.println("You can only choose 1, 2, or 3.");
                firstChoice(gameController);
                break;

        }
    }
}