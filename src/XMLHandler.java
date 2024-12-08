import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {
    private Document document;

    public XMLHandler(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getSceneById(String id) {
        NodeList scenes = document.getElementsByTagName("scene");
        for (int i = 0; i < scenes.getLength(); i++) {
            Element sceneElement = (Element) scenes.item(i);
            if (sceneElement.getAttribute("id").equals(id)) {
                String firstVisit = sceneElement.getElementsByTagName("firstVisit").getLength() > 0
                        ? sceneElement.getElementsByTagName("firstVisit").item(0).getTextContent()
                        : null;

                String secondVisit = sceneElement.getElementsByTagName("SecondVisit").getLength() > 0
                        ? sceneElement.getElementsByTagName("SecondVisit").item(0).getTextContent()
                        : null;

                String prompt = sceneElement.getElementsByTagName("prompt").getLength() > 0
                        ? sceneElement.getElementsByTagName("prompt").item(0).getTextContent()
                        : null;

                List<Option> options = new ArrayList<>();
                NodeList optionNodes = sceneElement.getElementsByTagName("option");
                for (int j = 0; j < optionNodes.getLength(); j++) {
                    Element optionElement = (Element) optionNodes.item(j);
                    String text = optionElement.getTextContent().trim();
                    String nextScene = optionElement.getAttribute("nextScene");
                    options.add(new Option(text, nextScene));
                }

                List<RandomEvent> randomEvents = new ArrayList<>();
                NodeList eventNodes = sceneElement.getElementsByTagName("randomEvent");
                for (int j = 0; j < eventNodes.getLength(); j++) {
                    Element eventElement = (Element) eventNodes.item(j);
                    int probability = Integer.parseInt(eventElement.getAttribute("probability"));
                    String eventText = eventElement.getTextContent();
                    randomEvents.add(new RandomEvent(probability, eventText));
                }

                return new Scene(id, firstVisit, secondVisit, prompt, options, randomEvents);
            }
        }
        return null;
    }
}