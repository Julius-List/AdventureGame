public class Main {
    public static void main(String[] args) {
        // Brug raw GitHub URL til at hente XML filen
        XMLHandler xmlHandler = new XMLHandler("https://raw.githubusercontent.com/Julius-List/AdventureGame/refs/heads/XML/src/AdventureStory.xml");

        // Opret GameController og giv den XMLHandler
        GameController game = new GameController(xmlHandler);

        // Start spillet
        GameController.start();
    }
}
