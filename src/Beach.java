public class Beach extends BaseLocation {
    private GameController gameController; // Reference til gameController så vi kan bruge returnToStart()
    private final Player player; // Reference til Player så vi kan håndtere health.

    public Beach(Item playerItems, GameController gameController, Player player) {
        super("Beach", playerItems);
        this.gameController = gameController;
        this.player = player;
    }

    @Override
    public void enter() {
        System.out.println("You stay on the " + locationName + " but the sun is scorching.");
        handleChoices();  // Call the specific choice handler for the beach
    }

    @Override
    public void handleChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Stay");
        System.out.println("2: Go right");
        System.out.println("3: Go back");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You stay on the beach for the rest of the day, just sitting in the sand. " +
                        "You begin to feel confused and nauseous as a heatstroke develops. \nBut you stay.");
                // Call GameOver
                break;
            case 2:
                System.out.println("You start exploring the beach, and find a necklace wedged between some drywood. " +
                        "You put the necklace in your pocket and start gathering the wood and find palm leaves.");
                playerItems.addItem("Necklace");
                playerItems.addItem("Wood and palm leaves");
                // Call på makeACamp method som indeholder if Lighter = create fire & camp, else create camp + random med create fire from palm leaves & stick.
                break;
            case 3:
                System.out.println("Choose a new path");
                // Option: Sea or Jungle location.
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                handleChoices();
                break;
        }
    }
}

