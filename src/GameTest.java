public class GameTest {
    public static void main(String[] args) {
        // Initialiser XMLHandler med den lokale fil
        XMLHandler xmlHandler = new XMLHandler("src/AdventureStory.xml");

        // Hent startscenen
        Location startLocation = xmlHandler.getLocationById("Start");

        // Vis scenen
        if (startLocation != null) {
            startLocation.display();
        } else {
            System.out.println("Location not found.");
        }
    }
}