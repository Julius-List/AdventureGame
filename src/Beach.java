public class Beach extends BaseLocation {
    private final GameController gameController; // Reference til gameController så vi kan kalde dens metoder
    private final Player player; // Reference til Player så vi kan håndtere health.

    public Beach(String locationName, GameController gameController, Player player) {
        super(locationName);
        this.gameController = gameController;
        this.player = player;
    }

    // Overrides so a unique entry message can be shown
    @Override
    protected String getEntryMessage() {
        return "You stay on the " + locationName + " but the sun is scorching and you start feeling dizzy.";
    }

    @Override
    public void handleChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Stay");
        System.out.println("2: Go right");
        System.out.println("3: Go left");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You stay on the beach for the rest of the day, just sitting in the sand. " +
                        "You begin to feel confused and nauseous as a heatstroke develops. \nBut you stay put.");
                player.loseHealth(player.getHealth()); // Player loses his current health
                break;
            case 2:
                System.out.println("You start exploring the beach, and find a necklace wedged between some drywood. " +
                        "You put the necklace in your pocket and start gathering the wood and find palm leaves.");
                player.getInventory().addItem(Item.NECKLACE);
                player.getInventory().addItem(Item.WOOD_AND_PALM_LEAVES);
                makeCampEvent();
                break;
            case 3:
                System.out.println("You walk left and look around, but you don't anything of use.");
                beachWalkLeftChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(3);
                handleChoices();
                break;
        }
    }

    public void beachWalkLeftChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1: Go back");
        System.out.println("2: Keep going");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You walk back to where you just came from.");
                handleChoices();
                break;
            case 2:
                System.out.println("You decide to keep on going.");
                System.out.println("As you continue walking, you find and old camp. \nClearly, someone has been " +
                                "staying here shortly before you arrived.");
                campChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(2);
                beachWalkLeftChoices();
                break;
        }
    }

    public void campChoices() {
        System.out.println("Do you stay away from the camp or do you search it?");
        System.out.println("1: Stay away from the camp");
        System.out.println("2: Search the camp");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You decide to stay away from the camp and walk back to where you woke up.");
                handleChoices();
                break;
            case 2:
                System.out.println("You decide to search the camp.\nYou find some food that looks edible and a " +
                "warm place to take cover from the cold night.\nYou also find a journal which clearly belonged" +
                        "to a person living in the camp.\nYou fall asleep and feel refreshed when you wake up");
                player.gainHealth(3);
                System.out.println("You grab what you can carry of essentials and walk back to where you came from on the beach.");
                player.getInventory().addItem(Item.STOLEN_JOURNAL);
                handleChoices();
                break;
            default:
                gameController.printInvalidChoiceMessage(2);
                campChoices();
                break;
        }
    }

    public void makeCampEvent() {
        System.out.println("After a lot of struggle, you manage to build a makeshift camp out of the wood and leaves.");
        inventoryDependentEvent();
    }

    // If player has a lighter, they create a fire, otherwise they attempt to create one without
    public void inventoryDependentEvent() {
        if (player.getInventory().containsItem(Item.LIGHTER.getName())) {
            startFireWithLighter();
        } else {
            startFireWithoutLighter();
        }
    }

    public void startFireWithLighter() {
        System.out.println("With the lighter from your pocket, you get a fire started to keep you warm.");
    }

    public void startFireWithoutLighter() {
        System.out.println("You attempt to start a fire with a stick and some leaves...");

        int chance = random.nextInt(4); // 25% chance to succeed in making a fire

        if (chance == 0) {
            System.out.println("After many failed attempts, you manage to get a spark going and build a fire!");
            wakeUpByFire();
        } else {
            System.out.println("No matter how hard you try, you can't get the leaves to ignite. You shiver " +
                    "through the night, but manage to get through with some palm leaves as cover. ");
            System.out.println("You continue exploring the beach and find a broken but functional fishing rod!");
            player.getInventory().addItem(Item.FISHING_ROD);
            System.out.println("As you continue walking, you find and old camp. \nClearly, someone has been " +
                    "staying here shortly before you arrived.");
            campChoices();
            player.loseHealth(1);
        }
    }

    public void wakeUpByFire() {
        System.out.println("You sleep as comfortably as one can do on the beach.");
        player.gainHealth(1);

        int chance = random.nextInt(4);

        if (chance == 0) {
            System.out.println("\n...\nAs the fire crackles, you are suddenly woken up by the sound of a boat!" +
                    "\nThey noticed the fire!\nYou instantly stand up and start waving your arms, and the boat" +
                    "speeds up to come and rescue you from the island.");
            gameController.gameWon();
        } else {
            System.out.println("Despite the glowing fire, no help came during the night.");
            System.out.println("You die of cold and loneliness.");
            player.loseHealth(player.getHealth());
        }

    }
}
