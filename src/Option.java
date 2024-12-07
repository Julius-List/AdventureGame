public class Option {
    private String text;
    private String nextLocationId;

    public Option(String text, String nextLocationId) {
        this.text = text;
        this.nextLocationId = nextLocationId;
    }

    public String getText() {
        return text;
    }

    public String getNextLocationId() {
        return nextLocationId;
    }
}