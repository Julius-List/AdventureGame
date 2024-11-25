public class Option {
    private String id;                // Unique ID for the option
    private String nextScene;         // ID of the next scene if this option is chosen
    private String text;              // Description of the option

    public Option(String id, String nextScene, String text) {
        this.id = id;
        this.nextScene = nextScene;
        this.text = text;
    }

    // Getters to access option properties
    public String getId() {
        return id;
    }

    public String getNextScene() {
        return nextScene;
    }

    public String getText() {
        return text;
    }
}
