public class Option {
    private String id;             // Unikt id for valgmuligheden
    private String nextScene;      // ID for den næste scene, som denne valgmulighed leder til
    private String text;           // Tekst der beskriver valget

    public Option(String id, String nextScene, String text) {
        this.id = id;
        this.nextScene = nextScene;
        this.text = text;
    }

    // Gettere til at få adgang til valgmulighedernes egenskaber
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
