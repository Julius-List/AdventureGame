public class DebugTest {

    public static void main(String[] args) {
        // Opret og initialiser objekter
        GameController gameController = new GameController();
        Player player = new Player(gameController);
        Inventory inventory = new Inventory();
        Jungle jungle = new Jungle("Jungle", gameController, player);

        // ---- Test Inventory ----
        System.out.println("---- Test Inventory ----");
        Item sword = new Item("Sword");
        Item shield = new Item("Shield");

        System.out.println("Tilføjer Sword til inventar...");
        inventory.addItem(sword);
        System.out.println("Inventar indeholder Sword? " + inventory.containsItem("Sword"));

        System.out.println("Tilføjer Shield til inventar...");
        inventory.addItem(shield);
        System.out.println("Inventar indeholder Shield? " + inventory.containsItem("Shield"));

        System.out.println("Forsøger at tilføje Sword igen...");
        inventory.addItem(sword);
        System.out.println("Antal genstande i inventar: " + inventory.items.size());

        // ---- Test Player ----
        System.out.println("\n---- Test Player ----");
        System.out.println("Spillerens starthelbred: " + player.getHealth());

        System.out.println("Reducerer spillerens helbred med 1...");
        player.loseHealth(1);
        System.out.println("Spillerens helbred efter tab: " + player.getHealth());

        System.out.println("Tilføjer helbred til spiller (2 point)...");
        player.gainHealth(2);
        System.out.println("Spillerens helbred efter forøgelse: " + player.getHealth());

        System.out.println("Reducerer spillerens helbred til 0...");
        player.loseHealth(10);
        System.out.println("Spillerens helbred efter tab: " + player.getHealth());

        // ---- Test Jungle ----
        System.out.println("\n---- Test Jungle ----");
        String entryMessage = jungle.getEntryMessage();
        System.out.println("Indgangsbesked for Jungle: " + entryMessage);

        System.out.println("Tilføjer Lighter til spillerens inventar...");
        player.getInventory().addItem(Item.LIGHTER);
        System.out.println("Spillerens inventar indeholder Lighter? " + player.getInventory().containsItem("Lighter"));

        // Debug afsluttet
        System.out.println("\n---- Debug Test Afsluttet ----");
    }
}