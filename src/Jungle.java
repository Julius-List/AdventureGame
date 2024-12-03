public class Jungle extends BaseLocation {
    private final GameController gameController; // Reference to gameController so we can call its methods
    private final Player player; // Reference til Player so we can manage health and inventory

    public Jungle(String locationName, GameController gameController, Player player) {
        super(locationName);
        this.gameController = gameController;
        this.player = player;
    }

    // Overrides so a unique entry message can be shown
    @Override
    protected String getEntryMessage() {
        return "You walk into the " + locationName + "." + "\nAs you step in you feel like you are being watched.";
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
                player.getInventory().addItem(Item.LIGHTER);
                foundLighterChoices();
                break;
            case 2:
                System.out.println("You decide to take the path to your right.\nYou notice that one of the trees you walk past is bearing fruits.");
                handleFruitChoices();
                break;
            case 3:
                System.out.println("You can't shake the feeling that something is wrong. You decide to go back to the beach.");
                gameController.showStartChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(3);
                handleChoices();
                break;
        }
    }

    private void handleFruitChoices() {
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
                handleAfterFruitTree();
            } else {
                System.out.println("You try to grab one of the fruits. As you take a bite, you feel nourished.");
                player.gainHealth(1);
                handleAfterFruitTree();
            }
        } else if (fruitChoice == 2) {
            System.out.println("You decide to ignore the fruit tree and continue through the jungle.");
            continueJungleChoices();
        } else {
            gameController.printInvalidChoiceMessage(2);
            handleChoices();
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
                gameController.showStartChoices();
                break;
            case 2:
                System.out.println("You continue deeper into the jungle, ready for the unknown.");
                continueJungleChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(2);
                foundLighterChoices();
                break;
        }
    }

    public void handleAfterFruitTree() {
        System.out.println("\nYou continue walking and stumble upon a leaf. A pretty leaf... You put it in your pocket.");
        player.getInventory().addItem(Item.PRETTY_LEAF);

        int chance = random.nextInt(4); // Generates a random integer between 1-4

        if (chance < 3) { // If the random generated number is < 3 (75% chance), the game is won
            System.out.println("\nAll of a sudden you hear the sound of a horn coming from the beach. A ship? Could it be?" +
                    "\nYou start sprinting back to the beach where you see a vessel in the sea. You jump and wave with " +
                    "your arms, and the ship notices you.\n");
            gameController.gameWon();
        } else { // If the random generated number is not < 3 (25% chance), the game is lost
            System.out.println("\nAs you are marching through the foliage, you notice your skin starts to flare up " +
                    "after touching the leaf. \nYou feel your throat swelling and you start suffocating.\n");
            player.loseHealth(player.getHealth()); // Player loses his current health
        }
    }

    private void continueJungleChoices() {
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
                gameController.printInvalidChoiceMessage(2);
                continueJungleChoices();
                break;
        }
    }

    private void drinkFromStreamChoices() {
        handleDrinkFromStreamOutcomes();
        System.out.println("What would you like to do next?");
        System.out.println("1: Continue through the jungle");
        System.out.println("2. Walk back to the beach");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                tarantulaChoices();
                break;
            case 2:
                System.out.println("You decide to head back to the beach.");
                gameController.showStartChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(2);
                drinkFromStreamChoices();
                break;
        }
    }

    private void handleDrinkFromStreamOutcomes() {
        int chance = random.nextInt(4);

        if (chance == 0) {
            System.out.println("You bow down and drink from the stream, but the taste of the water is foul.");
            player.loseHealth(1);
        } else {
            System.out.println("You bow down and drink from the stream. You feel nourished.");
            player.gainHealth(1);
        }
    }

    private void tarantulaChoices() {
        System.out.println("You continue your trek in the jungle. As you walk, you feel something itching on your neck.");
        System.out.println("1: Ignore the feeling");
        System.out.println("2. Itch your neck");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                fishingHatEvent();
                break;
            case 2:
                System.out.println("As you try to touch the tingling spot on your neck, you hit a tarantula " +
                        "crawling on your shoulder which angrily bites back and jumps off your shoulder and " +
                        "disappears into some leaves on the forest floor.");
                player.loseHealth(1);
                fishingHatEvent();
                break;
                default:
                    gameController.printInvalidChoiceMessage(2);
                    tarantulaChoices();
                    break;
        }
    }
    private void fishingHatEvent() {
        System.out.println("You continue walking, looking for anything of use.\nYou find a fishing hat. " +
            "Neat! You put it in your pocket.");
        player.getInventory().addItem(Item.FISHING_HAT);
        System.out.println("\nYou decide to continue foraging, but the jungle is too dense to walk through. " +
            "It is time to walk back to the beach...");
        gameController.showStartChoices();
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
                handleRestChoices();
                break;
            case 2:
                System.out.println("Fatigued, you make it to the top of the hill. It is almost completely dark now.\n" +
                        "As you scout the area, you notice a small village of indigenous people with lit torches by " +
                        "the bottom of the hill.");
                handleTopOfHillChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(2);
                hillChoices();
                break;
        }
    }

    private void handleTopOfHillChoices() {
        System.out.println("1: Turn around and go back");
        System.out.println("2: Walk closer to the village");

        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("As you turn around and descend from the hill, your body gives up...\"");
            player.loseHealth(player.getHealth()); // Player loses his current health
        } else if (choice == 2) {
            inventoryDependentEvent();
        } else {
            gameController.printInvalidChoiceMessage(2);
        }
    }

    private void handleRestChoices() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("1: Rest");

            int restChoice = scanner.nextInt();

            if (restChoice == 1) {
                System.out.println("As you sit down by one of the many trees, you feel relaxed and close your " +
                        "eyes.\nThe darkness and cold surrounds you.");
                player.loseHealth(player.getHealth()); // Player loses his current health
                validInput = true;
            } else {
                gameController.printInvalidChoiceMessage(1);
            }
        }
    }

    private void inventoryDependentEvent() {
        if (player.getInventory().containsItem(Item.PRETTY_ROCK.getName())) {
            System.out.println("On your last legs, you make it to the village and approach the villagers. " +
                    "You take the pretty rock that you found in the sea out of your pocket and present it to the " +
                    "villagers.\nThe villagers greet you with food and water and helps you return home.");
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
            System.out.println("1: Attempt to climb the ladder");
            System.out.println("2: Continue walking on the beach");
            lookoutPostEvent();
        }
    }

    public void lookoutPostEvent() {
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You attempt to climb the ladder.\n...");
                handleLadderClimb();
                break;
            case 2:
                System.out.println("You continue walking on the beach and end up where you woke up.");
                gameController.showStartChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(2);
                lookoutPostEvent();
                break;
        }
    }

    public void handleLadderClimb() {
        int chance = random.nextInt(5);

        if (chance == 0) { // 20% chance
            System.out.println("The ladder breaks halfway up and you fall onto your back on the sand. Ouch!");
            player.loseHealth(1);
            System.out.println("\nDisoriented, you blink a few times and get up. Better not try that again." +
                    "\nYou look around. This part of the beach seems familiar...\n" +
                    "You are back at where you woke up!");
            gameController.showStartChoices();
        } else { // 80% chance
            System.out.println("You make it all the way to the top of the outpost. You find " +
                    "a map lying on the wooden floor with a marked spot.\n" + "You put it in your pocket.");
            player.getInventory().addItem(Item.STRANGE_MAP);
            System.out.println("You carefully crawl down again and start your search for the marked location.");
            System.out.println("You walk for a while on the vast beach. You spot what looks like a camp. This is the marked spot!");
            ((Beach) gameController.getBeachLocation()).campChoices(); // Casting to Beach object to enter campChoices()
        }
    }
}