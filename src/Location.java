import java.util.ArrayList;
import java.util.List;

public class Location {
    private String id;
    private String description;
    private List<Option> options;

    public Location(String id, String description) {
        this.id = id;
        this.description = description;
        this.options = new ArrayList<>();
    }

    // Tilføj en valgmulighed
    public void addOption(Option option) {
        options.add(option);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Option> getOptions() {
        return options;
    }

    // Metode til at vise denne location
    public void display() {
        System.out.println(description);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i).getText());
        }
    }
}