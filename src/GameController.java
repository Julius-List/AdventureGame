import java.util.Scanner;

public class GameController {
    Scanner scan = new Scanner(System.in);
    int input;

public void start() {
    System.out.println("Her skal der hentes en tekstfil med introduktion og guide...");

    input = scan.nextInt();
    switch(input) { //switch
        case 1: //kalder instance af beach
            Beach beachPath = new Beach();
            beachPath.enter();
            break;

        case 2: //kalder instance af sea
            Sea seaPath = new Sea();
            seaPath.enter();
            break;

        case 3: //kalder instance af jungle
            Jungle junglePath = new Jungle();
            junglePath.enter();
            break;


        default:
            System.out.println("You can only choose between: \n1: beach, 2: sea or 3: jungle.");
            break;

    }
}
}
