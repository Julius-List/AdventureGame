public class DebugTest {

    public static void main(String[] args) {
        // Initialiserer spilobjekter
        GameController gameController = new GameController();
        Player player = new Player(gameController);
        Inventory inventory = new Inventory();
        Jungle jungle = new Jungle("Jungle", gameController, player);

        // ---- Test Inventory ----
        System.out.println("---- Test Inventory ----");

        // Opretter items til test
        Item sword = new Item("Sword");
        Item shield = new Item("Shield");

        // Tilføjer og validerer items i inventory
        System.out.println("Adding sword to Inventory...");
        inventory.addItem(sword);
        assert inventory.containsItem("Sword") : "Inventory should contain sword";

        System.out.println("Adding shield to Inventory...");
        inventory.addItem(shield);
        assert inventory.containsItem("Shield") : "Inventory should contain shield'";

        // Test for duplikater
        System.out.println("Trying to add sword to Inventory again");
        inventory.addItem(sword);
        assert inventory.items.size() == 2 : "Sword shouldn't be able to add again; item size should be 2";

        // ---- Test Player ----
        System.out.println("\n---- Test Player ----");

        // Tjekker spillerens helbred
        System.out.println("Players start health: " + player.getHealth());
        assert player.getHealth() > 0 : "Players should be higher than 0";

        // Reducerer spillerens helbred
        System.out.println("Reduce health with 1");
        player.loseHealth(1);
        assert player.getHealth() == 9 : "Health should be 9 after 1 health point lost";

        // ---- Test Jungle ----
        System.out.println("\n---- Test Jungle ----");

        // Validerer junglens indgangsbesked
        String entryMessage = jungle.getEntryMessage();
        assert entryMessage != null && !entryMessage.isEmpty() : "Entry message should not be empty";
        System.out.println("Jungles entry message: " + entryMessage);

        // Test: Spilleren forsøger at udforske junglen uden nødvendige items
        System.out.println("Testing jungle without necessary items");
        try {
            jungle.explore(); // Simulerer en handling, der kræver items
            System.out.println("Error: jungle exploration failed, needed item not in Inventory");
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Tilføjer nødvendigt item og tester igen
        System.out.println("Adding lighter to inventory...");
        player.getInventory().addItem(Item.LIGHTER);
        assert player.getInventory().containsItem("Lighter") : "Lighter should be in inventory";

        System.out.println("Testing jungle with neccessary items");
        try {
            jungle.explore();
            System.out.println("Exploration of jungle was a success");
        } catch (Exception e) {
            System.out.println("Error: player should have the necessary item to explore jungle");
        }

        // ---- Kaotisk test ----
        System.out.println("\n---- Chaotic Testing ----");

        // Test med tilfældige genstande
        for (int i = 0; i < 100; i++) {
            Item randomItem = new Item("RandomItem" + i);
            inventory.addItem(randomItem);
            assert inventory.containsItem("RandomItem" + i) : "Inventory should contain RandomItem" + i;
        }
        assert inventory.items.size() == 102 : "Inventory should have 102 items.";

        // Fjern tilfældige genstande
        System.out.println("Removing Random items...");
        for (int i = 0; i < 50; i++) {
            inventory.removeItem("RandomItem" + i);
        }
        assert inventory.items.size() == 52 : "Inventory should now have 52 items";

        System.out.println("Monkey Testing successful");

        // Debug afsluttet
        System.out.println("\n---- Debug Test ended ----");
    }
}