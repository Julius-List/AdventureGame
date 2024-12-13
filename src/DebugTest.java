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
        System.out.println("Tilføjer Sword til inventar...");
        inventory.addItem(sword);
        assert inventory.containsItem("Sword") : "Inventar burde indeholde 'Sword'";

        System.out.println("Tilføjer Shield til inventar...");
        inventory.addItem(shield);
        assert inventory.containsItem("Shield") : "Inventar burde indeholde 'Shield'";

        // Test for duplikater
        System.out.println("Forsøger at tilføje Sword igen...");
        inventory.addItem(sword);
        assert inventory.items.size() == 2 : "Duplikater burde ikke tilføjes; størrelse burde være 2";

        // Edge case: Tilføje null-værdi
        System.out.println("Tilføjer null til inventar...");
        try {
            inventory.addItem(null);
            System.out.println("FEJL: Inventar burde ikke kunne indeholde null");
        } catch (Exception e) {
            System.out.println("Forventet fejl ved tilføjelse af null: " + e.getMessage());
        }

        // ---- Test Player ----
        System.out.println("\n---- Test Player ----");

        // Tjekker spillerens helbred
        System.out.println("Spillerens starthelbred: " + player.getHealth());
        assert player.getHealth() > 0 : "Spillerens starthelbred burde være større end 0";

        // Reducerer spillerens helbred
        System.out.println("Reducerer spillerens helbred med 1...");
        player.loseHealth(1);
        assert player.getHealth() == 9 : "Helbred burde være 9 efter tab af 1 point";

        // Øger spillerens helbred
        System.out.println("Tilføjer helbred til spiller (2 point)...");
        player.gainHealth(2);
        assert player.getHealth() == 11 : "Helbred burde være 11 efter forøgelse med 2 point";

        // Edge case: Reducerer helbred til negativ værdi
        System.out.println("Reducerer spillerens helbred til 0...");
        player.loseHealth(15); // Forudsat at spillerens starthelbred er 10
        assert player.getHealth() == 0 : "Helbred burde ikke kunne være negativt";

        // ---- Test Jungle ----
        System.out.println("\n---- Test Jungle ----");

        // Validerer junglens indgangsbesked
        String entryMessage = jungle.getEntryMessage();
        assert entryMessage != null && !entryMessage.isEmpty() : "Indgangsbesked burde ikke være tom";

        // Tilføjer et item, som kræves i junglen
        System.out.println("Tilføjer Lighter til spillerens inventar...");
        player.getInventory().addItem(Item.LIGHTER);
        assert player.getInventory().containsItem("Lighter") : "Inventar burde indeholde 'Lighter'";

        // Edge case: Spilleren mangler et vigtigt item
        System.out.println("Fjerner Lighter fra spillerens inventar...");
        player.getInventory().removeItem("Lighter");
        assert !player.getInventory().containsItem("Lighter") : "Lighter burde være fjernet fra inventar";

        // ---- Kaotisk test: Monkey Testing ----
        System.out.println("\n---- Monkey Testing ----");

        // Tilføjer 100 tilfældige items for at teste grænser
        for (int i = 0; i < 100; i++) {
            Item randomItem = new Item("RandomItem" + i);
            inventory.addItem(randomItem);
        }
        assert inventory.items.size() == 102 : "Inventar burde indeholde 102 genstande (inkl. Sword og Shield)";

        // Debug afsluttet
        System.out.println("\n---- Debug Test Afsluttet ----");
    }
}