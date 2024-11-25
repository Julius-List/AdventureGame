import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {
    private Document document;

    public XMLHandler(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(inputStream);
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getSceneById(String id) {
        NodeList nList = document.getElementsByTagName("scene");
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (element.getAttribute("id").equals(id)) {
                    String firstVisit = getElementText(element, "firstVisit");
                    String secondVisit = getElementText(element, "SecondVisit");
                    String prompt = getElementText(element, "prompt");

                    // Get options for the scene
                    List<Option> options = new ArrayList<>();
                    NodeList optionNodes = element.getElementsByTagName("option");
                    for (int j = 0; j < optionNodes.getLength(); j++) {
                        Element optionElement = (Element) optionNodes.item(j);
                        String optionId = optionElement.getAttribute("id");
                        String nextScene = optionElement.getAttribute("nextScene");
                        String optionText = optionElement.getTextContent().trim();
                        options.add(new Option(optionId, nextScene, optionText));
                    }

                    // Get random events for the scene, if any
                    List<RandomEvent> randomEvents = new ArrayList<>();
                    NodeList randomEventNodes = element.getElementsByTagName("randomEvent");
                    if (randomEventNodes.getLength() > 0) {
                        NodeList eventNodes = ((Element) randomEventNodes.item(0)).getElementsByTagName("event");
                        for (int k = 0; k < eventNodes.getLength(); k++) {
                            Element eventElement = (Element) eventNodes.item(k);
                            String probabilityStr = eventElement.getAttribute("probability");
                            int probability = Integer.parseInt(probabilityStr);
                            String eventText = eventElement.getTextContent().trim();
                            randomEvents.add(new RandomEvent(probability, eventText));
                        }
                    }

                    return new Scene(id, firstVisit, secondVisit, prompt, options, randomEvents);
                }
            }
        }
        return null;
    }

    private String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }
}
