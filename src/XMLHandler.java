import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLHandler {
    private Document document;

    public XMLHandler(String filePath) {
        try {
            // Åbn lokal fil
            File xmlFile = new File(filePath);

            // Brug DocumentBuilder til at parse filen
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);

            // Normaliser dokumentet for at håndtere whitespace
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eksempel på metode til at hente en scene efter ID
    public Node getSceneById(String id) {
        NodeList scenes = document.getElementsByTagName("scene");
        for (int i = 0; i < scenes.getLength(); i++) {
            Element scene = (Element) scenes.item(i);
            if (scene.getAttribute("id").equals(id)) {
                return scene;
            }
        }
        return null;
    }

    public Location getLocationById(String id) {
        System.out.println("Looking for location with ID: " + id); // Debug
        Node sceneNode = getSceneById(id);
        if (sceneNode != null) {
            Element sceneElement = (Element) sceneNode;
            String description = sceneElement.getElementsByTagName("firstVisit").item(0).getTextContent();

            Location location = new Location(id, description);

            NodeList options = sceneElement.getElementsByTagName("option");
            for (int i = 0; i < options.getLength(); i++) {
                Element optionElement = (Element) options.item(i);
                String text = optionElement.getTextContent();
                String nextId = optionElement.getAttribute("id");
                System.out.println("Adding option: " + text + " -> " + nextId); // Debug
                location.addOption(new Option(text, nextId));
            }

            return location;
        }
        System.out.println("Location not found: " + id); // Debug
        return null;
    }
}

