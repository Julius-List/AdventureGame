import java.util.Scanner;

public class Sea {
    Scanner scan = new Scanner(System.in);
    int input;

    public void enter() {
        input = scan.nextInt();
        System.out.println("You walk towards the sea...");
        System.out.println("You walk into the water. Is this a good idea?");

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
}
