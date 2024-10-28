public class Jungle extends BaseLocation {
    private GameController gameController; // Reference til gameController så vi kan bruge returnToStart()

    public Jungle(Item playerItems, GameController gameController) {
        super("Jungle", playerItems);
        this.gameController = gameController;

    }

    @Override
    public void enter() {
        System.out.println("You walk into the jungle. \nAs you step in you feel like you are being watched.");
        handleChoices();  // Call the choice handler for the jungle
    }

    @Override
    public void handleChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Go left");
        System.out.println("2: Go right");
        System.out.println("3: Go back to the beach");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You fall over a branch and tumble down a mudslide.\nYou find a lighter. How convenient!");
                playerItems.addItem("Lighter");
                foundLighterChoices();
                break;
            case 2:
                System.out.println("You decide to take the path to your right.\nYou notice that one of the trees is bearing fruits.");
                break;
            case 3:
                System.out.println("You can't shake the feeling that something is wrong. You walk back to the beach.");
                gameController.returnToStart();
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                handleChoices();  // Recursively call handleChoices() for invalid input
                break;
        }
    }

    public void foundLighterChoices() {
        System.out.println("What would you like to do next?");
        System.out.println("1: Walk back to the beach.");
        System.out.println("2: Continue deeper into the jungle.");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You decide to head back to the beach. Now with a lighter.");
                gameController.returnToStart();
                break;
            case 2:
                System.out.println("You continue deeper into the jungle, ready for the unknown.");
                deeperJungleChoices();  // Call a method for further exploration in the jungle
                break;
            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                foundLighterChoices();  // Re-prompt if input is invalid
                break;
    }

}

    private void deeperJungleChoices() {
        System.out.println("As you walk, you find a small stream of freshwater.");
        System.out.println("1: Drink from the stream");
        System.out.println("2: Ignore the stream and continue walking");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("RANDOM SKER HER");
                //Random
                break;

            case 2:
                System.out.println("You ignore the stream continue walking");

                break;

            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                deeperJungleChoices();  // Re-prompt for valid input
                break;



        }
    }
}