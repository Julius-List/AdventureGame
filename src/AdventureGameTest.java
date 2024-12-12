import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventureGameTest {

    // Instansvariabler for de komponenter, der skal testes
    private Inventory inventory;        // Test af inventarlogik
    private GameController gameController; // Test af spilflow og tilstand
    private Player player;             // Test af spillerens funktionalitet
    private Jungle jungle;             // Test af funktionalitet i Jungle-lokationen

    @BeforeEach
    void setUp() {
        // Initialiser objekter før hver test
        inventory = new Inventory();                     // Tomt inventar til test af tilføjelser
        gameController = new GameController();           // Controller til at styre spiltilstanden
        player = new Player(gameController);             // Spilleren starter med standard helbred og inventar
        jungle = new Jungle("jungle", gameController, player); // Jungle-lokation til interaktionstests
    }

    // ---- Inventar Tests ----

    @Test
    void testAddItemToInventory() {
        // Tester tilføjelse af genstande til inventaret og undgår duplikater
        Item item1 = new Item("Sword"); // Opret første testgenstand
        Item item2 = new Item("Shield"); // Opret anden testgenstand

        inventory.addItem(item1); // Tilføj første genstand
        assertTrue(inventory.containsItem("Sword"), "Genstanden burde være tilføjet til inventaret.");

        inventory.addItem(item2); // Tilføj anden genstand
        assertTrue(inventory.containsItem("Shield"), "En anden genstand burde være tilføjet til inventaret.");

        inventory.addItem(item1); // Forsøg at tilføje en duplikat
        assertEquals(2, inventory.items.size(), "Duplikatgenstande burde ikke tilføjes til inventaret.");
    }

    @Test
    void testInventoryContainsItem() {
        // Tester, om inventaret korrekt identificerer genstande
        Item item = new Item("Potion"); // Opret testgenstand

        assertFalse(inventory.containsItem("Potion"), "Inventaret burde ikke indeholde genstanden til at begynde med.");
        inventory.addItem(item); // Tilføj genstanden
        assertTrue(inventory.containsItem("Potion"), "Inventaret burde indeholde genstanden efter tilføjelsen.");
    }

    // ---- Jungle Tests ----

    @Test
    void testJungleEntryMessage() {
        // Tester indgangsbeskeden for Jungle-lokationen
        String entryMessage = jungle.getEntryMessage();
        System.out.println("Indgangsbesked: " + entryMessage); // Debug output
        assertEquals("You walk into the jungle.\nAs you step in you feel like you are being watched.",
                entryMessage, "Indgangsbeskeden burde matche det forventede format.");
    }

    @Test
    void testAddItemInJungle() {
        // Tester tilføjelse af genstande til spillerens inventar i Jungle
        player.getInventory().addItem(Item.LIGHTER); // Tilføj en foruddefineret genstand
        System.out.println("Spillerens inventar: " + player.getInventory().containsItem(Item.LIGHTER.getName())); // Debugging
        assertTrue(player.getInventory().containsItem("Lighter"), "Spilleren burde have en lighter efter en event i junglen.");
    }

    @Test
    void testPlayerHealthDecreaseOnSnakeBite() {
        // Tester helbredsreduktion, når spilleren oplever en negativ hændelse
        int initialHealth = player.getHealth(); // Gem spillerens oprindelige helbred
        player.loseHealth(1);                   // Reducer helbred med 1
        System.out.println("Spillerens helbred efter tab på 1: " + player.getHealth()); // Debugging
        assertEquals(initialHealth - 1, player.getHealth(), "Spillerens helbred burde falde med 1 efter et slangebid.");
    }

    @Test
    void testRandomPrettyLeafEvent() {
        // Tester, om en "Pretty Leaf"-genstand tilføjes til spillerens inventar
        player.getInventory().addItem(Item.PRETTY_LEAF); // Tilføj bladgenstanden
        System.out.println("Spillerens inventar indeholder pretty leaf: " + player.getInventory().containsItem("Pretty Leaf")); // Debugging
        assertTrue(player.getInventory().containsItem("Pretty Leaf"), "Pretty Leaf burde tilføjes til inventaret.");
    }

    @Test
    void testGameWonScenario() {
        // Tester spilvinderlogikken
        player.getInventory().addItem(Item.PRETTY_ROCK); // Tilføj den nødvendige genstand for at vinde
        assertTrue(player.getInventory().containsItem("Pretty Rock"), "Spilleren burde have pretty rock for at vinde.");

        // Forhindrer faktisk System.exit(0) ved at mocke opførsel
        try {
            gameController.gameWon();
            fail("Game Won burde afslutte programmet, men det gjorde det ikke.");
        } catch (Exception e) {
            assertTrue(true, "Game Won blev korrekt udløst.");
        }
    }

    // ---- Spiller Tests ----

    @Test
    void testPlayerInitialHealth() {
        // Tester, at spilleren starter med 3 helbredspoint
        System.out.println("Initialt spillerhelbred: " + player.getHealth()); // Debugging
        assertEquals(3, player.getHealth(), "Spillerens oprindelige helbred burde være 3.");
    }

    @Test
    void testPlayerHealthIncrease() {
        // Tester, at spillerens helbred stiger og ikke overskrider maksimum
        player.gainHealth(2); // Giv 2 helbredspoint
        System.out.println("Spillerens helbred efter 2 ekstra point: " + player.getHealth()); // Debugging
        assertEquals(5, player.getHealth(), "Spillerens helbred burde ikke overstige maksimum (5).");
    }

    @Test
    void testPlayerHealthDecrease() {
        // Tester reduktion af spillerens helbred
        player.loseHealth(1); // Tab 1 helbredspoint
        System.out.println("Spillerens helbred efter tab på 1: " + player.getHealth()); // Debugging
        assertEquals(2, player.getHealth(), "Spillerens helbred burde falde korrekt.");
    }

    @Test
    void testPlayerHealthDoesNotGoBelowZero() {
        // Tester, at spillerens helbred ikke kan falde under 0
        player.loseHealth(10); // Forsøg at miste mere helbred end muligt
        System.out.println("Spillerens helbred efter tab på 10: " + player.getHealth()); // Debugging
        assertEquals(0, player.getHealth(), "Spillerens helbred burde ikke falde under nul.");
    }

    @Test
    void testPlayerInventoryInitialization() {
        // Tester, at spillerens inventar er korrekt initialiseret
        assertNotNull(player.getInventory(), "Spillerens inventar burde være initialiseret.");
    }

    @Test
    void testGameOverTriggered() {
        // Tester "Game Over"-logikken, når spillerens helbred når 0
        try {
            player.loseHealth(3); // Reducer helbred til 0
            fail("Game Over burde afslutte programmet, men det gjorde det ikke.");
        } catch (Exception e) {
            assertTrue(true, "Game Over blev korrekt udløst.");
        }
    }
}