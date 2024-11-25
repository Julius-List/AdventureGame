import java.util.List;

public class Scene {
    private String id;                  // Unique ID of the scene
    private String firstVisit;          // Text for the first visit
    private String secondVisit;         // Text for second visit (optional)
    private String prompt;              // Main prompt of the scene
    private List<Option> options;       // List of available options for the scene
    private List<RandomEvent> randomEvents; // List of random events, if any

    public Scene(String id, String firstVisit, String secondVisit, String prompt, List<Option> options, List<RandomEvent> randomEvents) {
        this.id = id;
        this.firstVisit = firstVisit;
        this.secondVisit = secondVisit;
        this.prompt = prompt;
        this.options = options;
        this.randomEvents = randomEvents;
    }

    // Getters to access scene properties
    public String getId() {
        return id;
    }

    public String getFirstVisit() {
        return firstVisit;
    }

    public String getSecondVisit() {
        return secondVisit;
    }

    public String getPrompt() {
        return prompt;
    }

    public List<Option> getOptions() {
        return options;
    }

    public List<RandomEvent> getRandomEvents() {
        return randomEvents;
    }
}
