public class Main {
    public static void main(String[] args) {
        // Initialiser XMLHandler én gang
        XMLHandler xmlHandler = new XMLHandler("src/AdventureStory.xml");

        // Initialiser GameController med XMLHandler
        GameController gameController = new GameController(xmlHandler);

        // Start spillet
        gameController.start();
    }
}