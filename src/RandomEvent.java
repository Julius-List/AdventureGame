public class RandomEvent {
    private int probability; // Probability of the event happening (as percentage)
    private String eventText; // Text describing the event

    public RandomEvent(int probability, String eventText) {
        this.probability = probability;
        this.eventText = eventText;
    }

    public int getProbability() {
        return probability;
    }

    public String getEventText() {
        return eventText;
    }
}
