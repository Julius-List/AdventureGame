import java.util.List;

public class Scene {
    private String id;              // Unikt id for scenen (f.eks. "Jungle" eller "Start")
    private String firstVisit;      // Tekst for første besøg i scenen
    private String secondVisit;     // Tekst for andet besøg (kan være null)
    private String prompt;          // Prompt tekst, der beskriver situationen (kan være null)
    private List<Option> options;   // Liste over valgmuligheder, som spilleren kan vælge

    public Scene(String id, String firstVisit, String secondVisit, String prompt, List<Option> options) {
        this.id = id;
        this.firstVisit = firstVisit;
        this.secondVisit = secondVisit;
        this.prompt = prompt;
        this.options = options;
    }

    // Gettere til at få adgang til scenernes egenskaber
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
}
