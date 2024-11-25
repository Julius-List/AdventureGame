public class Jungle extends BaseLocation {
    private GameController gameController; // Reference til gameController så vi kan kalde dens metoder
    private final Player player; // Reference til Player så vi kan håndtere health.

    public Jungle(Item playerItems, GameController gameController, Player player) {
        super("Jungle", playerItems);
        this.gameController = gameController;
        this.player = player;
    }

    @Override
    public void enter() {
        System.out.println("You walk into the " + locationName + "." + "\nAs you step in you feel like you are being watched.");
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
                        System.out.println("As you try to grab a fruit, a snake lunges out from the tree and bites your hand!");
                        player.loseHealth(1);
                        System.out.println("Your hand hurts, but you got to keep on moving.");
                        afterFruitTree();
                    } else {
                        System.out.println("You try to grab one of the fruits. As you take a bite, you feel nourished.");
                        player.gainHealth(1);
                        afterFruitTree();
                    }
                } else if (fruitChoice == 2) {
                    System.out.println("You decide to ignore the fruit tree and continue through the jungle.");
                    continueJungleChoices1();
                } else {
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    handleChoices();
                }
                break;
            case 3:
                System.out.println("You can't shake the feeling that something is wrong. You decide to go back to the beach.");
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
                continueJungleChoices1();
                break;
            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                foundLighterChoices();
                break;
        }
    }

    public void afterFruitTree() {
        System.out.println("\nYou continue walking and stumble upon a leaf. A pretty leaf... You put it in your pocket.");
        playerItems.addItem("Pretty leaf");

        int chance = random.nextInt(4);

        if (chance < 3) {
            System.out.println("\nAll of a sudden you hear the sound of a horn coming from the beach. A ship? Could it be?" +
                    "\nYou start sprinting back to the beach where you see a vessel in the sea. You jump and wave with " +
                    "your arms, and the ship notices you.\n");
            gameController.gameWon();
        } else {
            System.out.println("\nAs you are marching through the foliage, you notice your skin starts to flare up " +
                    "after touching the leaf. \nYou feel your throat swelling and you start suffocating.\n");
            gameController.gameOver();
        }
    }

    private void continueJungleChoices1() {
        System.out.println("As you walk, you come across a small stream of freshwater.");
        System.out.println("1: Drink from the stream");
        System.out.println("2: Ignore the stream and continue walking");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                drinkFromStreamChoices();
                break;
            case 2:
                System.out.println("You ignore the stream and continue walking. It is getting dark.\n...");
                System.out.println("As you continue, you become exhausted from walking in the tall grass.");
                player.loseHealth(1);
                System.out.println("You reach a small hill.");
                hillChoices();
                break;
            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                continueJungleChoices1();
                break;
        }
    }

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
                continueJungleChoices1();
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

    private void hillChoices() {
        System.out.println("1: Walk along the hill");
        System.out.println("2: Walk up the hill");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("As you continue to walk along the hill you daydream about the fruit tree that " +
                        "you chose to ignore earlier. \nIt is almost completely dark now and you can barely stand on " +
                        "your feet from sheer exhaustion.");
                boolean validInput = false;

                while (!validInput) {
                    System.out.println("1: Rest");

                    int restChoice = scanner.nextInt();

                    if (restChoice == 1) {
                        System.out.println("As you sit down by one of the many trees, you feel relaxed and close your " +
                                "eyes.\nThe darkness and cold surrounds you.");
                        gameController.gameOver();
                        validInput = true;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                break;
            case 2:
                System.out.println("Fatigued, you make it to the top of the hill. It is almost completely dark now.\n" +
                        "As you scout the area, you notice a small village of indigenous people with lit torches by " +
                        "the bottom of the hill.");
                System.out.println("1: Turn around and go back");
                System.out.println("2: Walk closer to the village");

                int topOfHillChoice = scanner.nextInt();

                if (topOfHillChoice == 1) {
                    System.out.println("As you turn around and descend from the hill, your body gives up...");
                    gameController.gameOver();
                } else if (topOfHillChoice == 2) {
                    inventoryDependentChoice();
                } else {
                    System.out.println("Invalid choice. Please select 1 or 2.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                hillChoices();
                break;
        }
    }

    private void inventoryDependentChoice() {
        if (playerItems.containsItem("Pretty rock")) {
            System.out.println("On your last legs, you make it to the village and approach the villagers. " +
                    "You take the pretty rock that you found in the sea out of your pocket and present it to the " +
                    "villagers.\nThe villagers greet you with food and water and helps you return home");
            gameController.gameWon();
        } else {
            System.out.println("You walk closer but realise that the villagers are weary of you and decide not to " +
                    "approach as you have nothing to offer. You have to walk back, despite your exhaustion.\n\n" +
                    "You find a small secluded area and steal some food before hiding under a giant tree.");

            player.gainHealth(1);

            System.out.println("\nYou wake up feeling better, despite your body aching from sleeping on the cold ground. " +
                    "You know you must keep moving.\n" +
                    "As you walk, you find yourself back on the beach where you discover a lookout post. " +
                    "The ladder seems withered.\n\n" +
                    "Do you climb it, or decide against it and keep walking?");
        }
    }
}
