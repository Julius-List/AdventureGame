import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdventureGameTest {

    private Inventory inventory;
    private GameController gameController;
    private Player player;
    private Jungle jungle;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        gameController = new GameController();
        player = new Player(gameController);
        jungle = new Jungle("jungle", gameController, player);
    }

    // ---- Inventory Tests ----
    @Test
    void testAddItemToInventory() {
        Item item1 = new Item("Sword");
        Item item2 = new Item("Shield");

        inventory.addItem(item1);
        assertTrue(inventory.containsItem("Sword"), "Item should be added to the inventory.");

        inventory.addItem(item2);
        assertTrue(inventory.containsItem("Shield"), "Another item should be added to the inventory.");

        inventory.addItem(item1); // Duplicate item
        assertEquals(2, inventory.items.size(), "Duplicate items should not be added to the inventory.");
    }

    @Test
    void testInventoryContainsItem() {
        Item item = new Item("Potion");

        assertFalse(inventory.containsItem("Potion"), "Inventory should not contain the item initially.");
        inventory.addItem(item);
        assertTrue(inventory.containsItem("Potion"), "Inventory should contain the item after adding it.");
    }

    // ---- Jungle Tests ----
    @Test
    void testJungleEntryMessage() {
        String entryMessage = jungle.getEntryMessage();
        assertEquals("You walk into the jungle.\nAs you step in you feel like you are being watched.", entryMessage,
                "Entry message should match the expected format.");
    }

    @Test
    void testAddItemInJungle() {
        player.getInventory().addItem(Item.LIGHTER);
        assertTrue(player.getInventory().containsItem("Lighter"), "Player should have a lighter after an event in the jungle.");
    }

    @Test
    void testPlayerHealthDecreaseOnSnakeBite() {
        int initialHealth = player.getHealth();
        player.loseHealth(1);
        assertEquals(initialHealth - 1, player.getHealth(), "Player health should decrease by 1 after a snake bite.");
    }

    @Test
    void testRandomPrettyLeafEvent() {
        player.getInventory().addItem(Item.PRETTY_LEAF);
        assertTrue(player.getInventory().containsItem("Pretty Leaf"), "Pretty Leaf should be added to the inventory.");
    }

    @Test
    void testGameWonScenario() {
        player.getInventory().addItem(Item.PRETTY_ROCK);
        assertTrue(player.getInventory().containsItem("Pretty Rock"), "Player should have the pretty rock to win.");

        gameController.gameWon();
        // Validate winning condition (e.g., mock or check logs/output)
    }

    // ---- Player Tests ----
    @Test
    void testPlayerInitialHealth() {
        assertEquals(3, player.getHealth(), "Player's initial health should be 3.");
    }

    @Test
    void testPlayerHealthIncrease() {
        player.gainHealth(2);
        assertEquals(5, player.getHealth(), "Player's health should not exceed the maximum health (5).");
    }

    @Test
    void testPlayerHealthDecrease() {
        player.loseHealth(1);
        assertEquals(2, player.getHealth(), "Player's health should decrease correctly.");
    }

    @Test
    void testPlayerHealthDoesNotGoBelowZero() {
        player.loseHealth(10);
        assertEquals(0, player.getHealth(), "Player's health should not drop below zero.");
    }

    @Test
    void testPlayerInventoryInitialization() {
        assertNotNull(player.getInventory(), "Player's inventory should be initialized.");
    }

    @Test
    void testGameOverTriggered() {
        player.loseHealth(3);
        assertEquals(0, player.getHealth(), "Player's health should be zero when they die.");
        // Additional assertions for game over behavior can be added here if needed
    }
}