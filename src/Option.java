public class Option {
    private String text;       // Teksten for valget
    private String nextSceneId; // ID for næste scene

    // Constructor
    public Option(String text, String nextSceneId) {
        this.text = text;
        this.nextSceneId = nextSceneId;
    }

    // Getters
    public String getText() {
        return text;
    }

    public String getNextSceneId() {
        return nextSceneId;
    }
}
