
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventureGameTest {

    // Instansvariabler oprettes til de klasser der skal testes
    private Inventory inventory;
    private GameController gameController;
    private Player player;
    private Jungle jungle;

    @BeforeEach
    void setUp() {
        // Initialiser nye objekter før hver testkørsel
        inventory = new Inventory();
        gameController = new GameController();
        player = new Player(gameController);
        jungle = new Jungle("jungle", gameController, player);
    }

    @AfterEach
    void tearDown() {
        // Nulstil objekter efter hver testkørsel
        inventory = null;
        gameController = null;
        player = null;
        jungle = null;
    }

    // Inventory testes

    @Test
    void testAddItemToInventory() {
        // tester at genstande kan tilføjes og kan fjernes igen
        Item item1 = new Item("Sword");
        Item item2 = new Item("Shield");

        inventory.addItem(item1); //  tilføjer den første genstand
        assertTrue(inventory.containsItem("Sword"), "Sword has been added to your inventory");

        inventory.addItem(item2); // tilføjer endnu en genstand
        assertTrue(inventory.containsItem("Shield"), "Shield has been added to your inventory");

        inventory.addItem(item1); // Forsøger at tilføje en dublikant
        assertEquals(2, inventory.items.size(), "You already have this item");
    }

    @Test
    void testInventoryContainsItem() {
        // Tester om Inventory korrekt kan identificere genstande
        Item item = new Item("Potion"); // Opretter test genstand

        assertFalse(inventory.containsItem("Potion"), "Potion can't be added to your inventory");
        inventory.addItem(item); // Tilføj genstanden
        assertTrue(inventory.containsItem("Potion"), "Potion has been added to your inventory");
    }

    // Jungle Tests

    @Test
    void testJungleEntryMessage() {
        // Tester om indgangsbesked til junglen er korrekt.
        String entryMessage = jungle.getEntryMessage();
        System.out.println("Testing entryMessage: " + entryMessage); // Debug output
        assertEquals("You walk into the jungle.\nAs you step in you feel like you are being watched.",
                entryMessage, "The entry message should match");
    }


    @Test
    void testRandomPrettyLeafEvent() {
        // Tester, om genstanden "Pretty Leaf" tilføjes korrekt til spillerens inventar
        player.getInventory().addItem(Item.PRETTY_LEAF);
        System.out.println("Player has Pretty Leaf in their inventory: " + player.getInventory().containsItem("Pretty Leaf")); // Debugging
        assertTrue(player.getInventory().containsItem("Pretty Leaf"), "Players inventory should have the 'Pretty Leaf'.");
    }

    @Test
    void testAddItemJungle() {
        // Tester, at en genstand kan tilføjes til spillerens inventar, når spilleren interagerer i junglen
        player.getInventory().addItem(Item.LIGHTER); // Tilføj genstanden "Lighter"
        System.out.println("Players inventory: " + player.getInventory().containsItem(Item.LIGHTER.getName())); // Debugging
        assertTrue(player.getInventory().containsItem("Lighter"), "Player should have Ligther after entering jungle");
    }

    @Test
    void testDecreaseOnSnakeBite() {
        // Tester om player mister liv efter mødet med slangen
        int initialHealth = player.getHealth(); // Gem spillerens nuværende helbred
        player.loseHealth(1);  // Reducer spillerens helbred med 1
        System.out.println("The players health after attack: " + player.getHealth()); // Debug output
        assertEquals(initialHealth - 1, player.getHealth(), "Player should have lost 1 health");
    }

    // Spiller Tests

    @Test
    void testPlayerInitialHealth() {
        // Tester, at player starter med 3 i health
        System.out.println("Players start health " + player.getHealth()); // Debug output
        assertEquals(3, player.getHealth(), "Player should have 3 health points from start.");
    }

    @Test
    void testPlayerHealthIncrease() {
        // Tester at health stiger korrekt
        player.gainHealth(2); // Giv spilleren 2 health points
        System.out.println("Player now has: " + player.getHealth()); // Debug output
        assertEquals(5, player.getHealth(), "Players health shouldn't be over 5 health points");
    }

    @Test
    void testPlayerHealthDecrease() {
        // Tester om players health bliver reduceret korrekt.
        player.loseHealth(1); // Reducer spillerens health med 1
        System.out.println("Health after attack: " + player.getHealth()); // Debug output
        assertEquals(2, player.getHealth(), "Players health should be reduced by one");
    }

    @Test
    void testPlayerHealthDoesNotGoBelowZero() {
        // Tester at players health ikke kan gå under 0
        player.loseHealth(10); // Reducer helbred med et større tal end det nuværende
        System.out.println("Players health after lot of damange: " + player.getHealth()); // Debug output
        assertEquals(0, player.getHealth(), "Players health can't go below 0.");
    }

    @Test
    void testPlayerInventoryInitialization() {
        // Tester, at spillerens inventory er initialiseret korrekt
        assertNotNull(player.getInventory(), "Players inventory should be initialized correctly");
    }

}
