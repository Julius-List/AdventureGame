public class Player {
    private int health;
    private final GameController gameController; // Calling GameController so we can manage game over logic
    private final Inventory inventory;

    public Player(GameController gameController) {
        this.health = 3;  // Spiller starter med 3 health points
        this.gameController = gameController;
        this.inventory = new Inventory(); // Initializes an empty inventory
    }

    // Getter for liv
    public int getHealth() {
        return health;
    }

    // Setter for liv
    public void setHealth(int health) {
        this.health = health;
    }

    // Spilleren mister liv eller dør
    public void loseHealth(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            gameController.gameOver(); // Calls game over method from GameController
        } else {
            System.out.println("\u001B[34mYou lost " + amount + " health. Current health: " + this.health + "\u001B[0m");
        }
    }

    // Spilleren får liv (max 5)
    public void gainHealth(int amount) {
        this.health += amount;
        if (this.health > 5) {
            this.health = 5;  // Man kan maks få 5 liv
        }
        System.out.println("\u001B[34mYou gained " + amount + " health. Current health: " + this.health + "\u001B[0m");
    }
    public Inventory getInventory() {
        return inventory;
    }
}



