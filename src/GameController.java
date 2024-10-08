import java.util.Scanner;

public class GameController {
    Scanner scan = new Scanner(System.in);
    int input;

public void start() {
    System.out.println("You wake up on the beach of a deserted island blablabla. \n1: Stay on the beach.\n2: Walk to the sea.\n3: Walk to the jungle.");

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
            System.out.println("You can only choose 1, 2, or 3.");
            break;

         }
    }
}
