public class Player {
    private int health;
    private static final int MAX_HEALTH = 5; // Constant for the maximum health value
    private final GameController gameController; // Calls GameController so we can manage game over logic
    private final Inventory inventory;

    // Constructor to initialize health and m¢anage game logic from GameController
    public Player(GameController gameController) {
        this.health = 3;  // The player starts with 3 health points
        this.gameController = gameController;
        this.inventory = new Inventory(); // Initializes an empty inventory
    }

    // Getter for health
    public int getHealth() {
        return health;
    }

    // Setter for health - prevents health from being negative or exceeding the maximum
    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else if (health > MAX_HEALTH) {
            this.health = MAX_HEALTH;
        } else {
            this.health = health;
        }
    }

    // Method to lose health
    public void loseHealth(int amount) {
        setHealth(getHealth() - amount);
        if (this.health <= 0) {
            gameController.gameOver(); // Calls game over method from GameController if health reaches 0
        } else {
            System.out.println("\u001B[34mYou lost " + amount + " health. Current health: " + this.health + "\u001B[0m");
        }
    }

    // Method to gain health
    public void gainHealth(int amount) {
        setHealth(getHealth() + amount);
        System.out.println("\u001B[34mYou gained " + amount + " health. Current health: " + this.health + "\u001B[0m");
    }

    // Getter for inventory
    public Inventory getInventory() {
        return inventory;
    }
}