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

        // ---- Test Player ----
        System.out.println("\n---- Test Player ----");

        // Tjekker spillerens helbred
        System.out.println("Spillerens starthelbred: " + player.getHealth());
        assert player.getHealth() > 0 : "Spillerens starthelbred burde være større end 0";

        // Reducerer spillerens helbred
        System.out.println("Reducerer spillerens helbred med 1...");
        player.loseHealth(1);
        assert player.getHealth() == 9 : "Helbred burde være 9 efter tab af 1 point";

        // ---- Test Jungle ----
        System.out.println("\n---- Test Jungle ----");

        // Validerer junglens indgangsbesked
        String entryMessage = jungle.getEntryMessage();
        assert entryMessage != null && !entryMessage.isEmpty() : "Indgangsbesked burde ikke være tom";
        System.out.println("Junglens indgangsbesked: " + entryMessage);

        // Test: Spilleren forsøger at udforske junglen uden nødvendige items
        System.out.println("Tester junglen uden nødvendige genstande...");
        try {
            jungle.explore(); // Simulerer en handling, der kræver items
            System.out.println("FEJL: Spilleren burde ikke kunne udforske junglen uden nødvendige items.");
        } catch (Exception e) {
            System.out.println("Forventet fejl: " + e.getMessage());
        }

        // Tilføjer nødvendigt item og tester igen
        System.out.println("Tilføjer Lighter til spillerens inventar...");
        player.getInventory().addItem(Item.LIGHTER);
        assert player.getInventory().containsItem("Lighter") : "Inventar burde indeholde 'Lighter'";

        System.out.println("Tester junglen med nødvendigt item...");
        try {
            jungle.explore();
            System.out.println("Udforskning af junglen lykkedes som forventet!");
        } catch (Exception e) {
            System.out.println("FEJL: Spilleren burde kunne udforske junglen med nødvendigt item.");
        }

        // ---- Kaotisk test: Monkey Testing ----
        System.out.println("\n---- Monkey Testing ----");

        // Test med tilfældige genstande
        for (int i = 0; i < 100; i++) {
            Item randomItem = new Item("RandomItem" + i);
            inventory.addItem(randomItem);
            assert inventory.containsItem("RandomItem" + i) : "Inventar burde indeholde RandomItem" + i;
        }
        assert inventory.items.size() == 102 : "Inventar burde have 102 genstande.";

        // Fjern tilfældige genstande
        System.out.println("Fjerner tilfældige items...");
        for (int i = 0; i < 50; i++) {
            inventory.removeItem("RandomItem" + i);
        }
        assert inventory.items.size() == 52 : "Efter fjernelse burde inventar have 52 genstande.";

        System.out.println("Monkey Testing afsluttet uden fejl!");

        // Debug afsluttet
        System.out.println("\n---- Debug Test Afsluttet ----");
    }
}