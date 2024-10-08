import java.util.Scanner;

public class Jungle {
    Scanner scan = new Scanner(System.in);
    int input;

    public void enter() {
        input = scan.nextInt();
        System.out.println("You walk towards the jungle...");
        System.out.println("As you step into the jungle, you feel like you are being watched.");
        System.out.println("1: Do you go back?\n2: Do you turn right?\n3: Do you turn left?");

        switch (input) {
            case 1:

                break;

            case 2:

                break;

            case 3:

                break;

            default:
                System.out.println("You can only choose 1, 2, or 3.");
                break;

        }
}
