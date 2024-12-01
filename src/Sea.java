public class Sea extends BaseLocation {
    private GameController gameController; // Reference til gameController så vi kan kalde dens metoder
    private final Player player; // Reference til Player så vi kan håndtere health.

    public Sea(GameController gameController, Player player) {
        super("Sea");
        this.gameController = gameController;
        this.player = player;
    }

    @Override
    public void enter() {
        System.out.println("You walk towards the " + locationName + " and into the shallow water. Is this a good idea?");
        handleChoices();
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

                int chance = random.nextInt(4);

                if (chance == 0) { // 25% chance
                    System.out.println("You feel exhausted from swimming. This is not what you want to do right now.");
                    player.loseHealth(1);
                    System.out.println("You barely make it back to the beach.");
                    gameController.returnToStart();
                } else if (chance == 1 || chance == 2) { // 50% chance
                    System.out.println("You find a fishing spot, but you have nothing to catch the fish with. " +
                            "\nYou found a rock. A pretty rock. You put it in your pocket.");
                    player.getInventory().addItem(Item.PRETTY_ROCK);
                    System.out.println("\nThere is nothing else for you to do in the water. You swim back to the beach.");
                    gameController.returnToStart();
                } else { // 25% chance
                    System.out.println("All of a sudden you are mauled from beneath by the jaws of a great white " +
                            "shark mistaking you for a sea turtle.");
                    gameController.gameOver();

            }
                break;
            case 2:
                System.out.println("With wet feet, you walk back to where you woke up.");
                gameController.returnToStart();
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                handleChoices();
                break;
        }
    }
}