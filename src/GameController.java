import java.util.Scanner;

public class GameController {
    int input;
    Scanner scan = new Scanner(System.in);

public void start() {
    input = scan.nextInt();
    switch(input) {
        case 1: //beach

        case 2: //sea

        case 3: //jungle

        default:
            System.out.println("You can only choose between: \n1: beach, 2: sea or 3: jungle.");
            break;

    }
}
}
