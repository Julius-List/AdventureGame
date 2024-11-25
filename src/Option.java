import java.util.List;

public class Option {
    private String id;
    private String nextScene;
    private String text;
    private List<RandomEvent> randomEvents; // List of possible random events

    public Option(String id, String nextScene, String text, List<RandomEvent> randomEvents) {
        this.id = id;
        this.nextScene = nextScene;
        this.text = text;
        this.randomEvents = randomEvents;
    }

    public String getId() {
        return id;
    }

    public String getNextScene() {
        return nextScene;
    }

    public String getText() {
        return text;
    }

    public List<RandomEvent> getRandomEvents() {
        return randomEvents;
    }
}
