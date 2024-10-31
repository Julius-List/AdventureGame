public class Jungle extends BaseLocation {
    private GameController gameController;
    private Player player;

    public Jungle(Item playerItems, GameController gameController, Player player) {
        super("Jungle", playerItems);
        this.gameController = gameController;
        this.player = player;
    }

    @Override
    public void enter() {
        System.out.println("You walk into the jungle. \nAs you step in you feel like you are being watched.");
        handleChoices();
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
                System.out.println("You decide to take the path to your right.\nYou notice that one of the trees you walk past is bearing fruits.");
                System.out.println("What would you like to do?");
                System.out.println("1: Try to grab the fruit");
                System.out.println("2: Continue through the jungle");

                int fruitChoice = scanner.nextInt();

                if (fruitChoice == 1) {
                    int chance = random.nextInt(4);

                    if (chance == 0) {
                        System.out.println("As you try to grab a fruit, a snake lunges out from the tree and bites your hand!\nYour hand hurts, but you got to keep on moving.");
                        player.loseHealth(1);
                        afterFruitTree();
                    } else {
                        System.out.println("You try to grab one of the fruits. As you take a bite, you feel nourished.");
                        player.gainHealth(1);
                        afterFruitTree();
                    }
                } else if (fruitChoice == 2) {
                    System.out.println("You decide to ignore the fruit tree and continue through the jungle.");
                    continueJungleChoices0();
                } else {
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    handleChoices();
                }
                break;

            case 3:
                System.out.println("You decide to go back to the beach.");
                gameController.returnToStart();
                break;

            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                handleChoices();
                break;
        }
    }

    public void foundLighterChoices() {
        System.out.println("What would you like to do next?");
        System.out.println("1: Walk back to the beach");
        System.out.println("2: Continue deeper into the jungle");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You decide to head back to the beach. Now with a lighter.");
                gameController.returnToStart();
                break;

            case 2:
                System.out.println("You continue deeper into the jungle, ready for the unknown.");
                continueJungleChoices0();
                break;

            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                foundLighterChoices();
                break;
        }
    }

    public void afterFruitTree() {
        System.out.println("You continue walking and stumble upon a leaf. A pretty leaf. You put it in your pocket.");
        playerItems.addItem("Pretty leaf");
        // GameOver logik skal ind her for at lave Random :))))
    }

    private void continueJungleChoices0() {
        System.out.println("As you walk, you come across a small stream of freshwater.");
        System.out.println("1: Drink from the stream");
        System.out.println("2: Ignore the stream and continue walking");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                drinkFromStreamChoices();
                break;

            case 2:
                System.out.println("You ignore the stream and continue walking. It is getting dark.");
                System.out.println("...");
                System.out.println("As you continue, you become exhausted from walking in the tall grass.");
                player.loseHealth(1);
                System.out.println("You reach a small hill.");
                // Call smallHillChoices method.
                break;

            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                continueJungleChoices0();
                break;
        }
    }

    // Case 1
    private void drinkFromStreamChoices() {
        int chance = random.nextInt(4);

        if (chance == 0) {
            System.out.println("You bow down and drink from the stream, but the taste of the water is foul.");
            player.loseHealth(1);
        } else {
            System.out.println("You bow down and drink from the stream. You feel nourished.");
            player.gainHealth(1);
        }

        System.out.println("What would you like to do next?");
        System.out.println("1: Continue through the jungle");
        System.out.println("2. Walk back to the beach");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                continueJungleChoices0();
                break;

            case 2:
                System.out.println("You decide to head back to the beach.");
                gameController.returnToStart();
                break;

            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                drinkFromStreamChoices();
                break;
        }
    }

    // Case 2
    private void smallHillChoices() {

    }
}
