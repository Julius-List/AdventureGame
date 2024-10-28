public class Sea extends BaseLocation {
    private GameController gameController; // Reference til gameController så vi kan bruge returnToStart()

    public Sea(Item playerItems, GameController gameController) {
        super("Sea", playerItems);
        this.gameController = gameController;
    }

    @Override
    public void enter() {
        System.out.println("You walk towards the sea and into the shallow water. Is this a good idea?");
        handleChoices();  // Call the choice handler for the sea
    }

    @Override
    public void handleChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Begin to swim");
        System.out.println("2: Go back");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You swim into the refreshing, cool waters." + "\n...");
                // Indsæt Random
                break;
            case 2:
                System.out.println("With wet feet, you walk back to where you woke up.");
                gameController.returnToStart();
                break;

            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                handleChoices();  // Recursively call handleChoices() for invalid input
                break;
        }
    }
}