public class Player {

    private int health;

    public Player() {
        this.health = 3;  // Spiller starter med 3 health points
    }

    // Getter for liv
    public int getHealth() {
        return health;
    }

    // Setter for liv
    public void setHealth(int health) {
        this.health = health;
    }

    // Spilleren mister liv
    public void loseHealth(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            System.out.println("You have died.");
        } else {
            System.out.println("You lost " + amount + " health. Current health: " + this.health);
        }
    }

    // Spilleren får liv (max 5)
    public void gainHealth(int amount) {
        this.health += amount;
        if (this.health > 5) {
            this.health = 5;  // Man kan maks få 5 liv
        }
        System.out.println("You gained " + amount + " health. Current health: " + this.health);
    }
}



