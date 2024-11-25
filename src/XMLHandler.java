import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

                    List<Option> options = new ArrayList<>();
                    NodeList optionNodes = element.getElementsByTagName("option");
                    for (int j = 0; j < optionNodes.getLength(); j++) {
                        Element optionElement = (Element) optionNodes.item(j);
                        String optionId = optionElement.getAttribute("id");
                        String nextScene = optionElement.getAttribute("nextScene");
                        String optionText = optionElement.getTextContent().trim();

                        // Extract random events if present
                        List<RandomEvent> randomEvents = new ArrayList<>();
                        NodeList randomEventNodes = optionElement.getElementsByTagName("event");
                        for (int k = 0; k < randomEventNodes.getLength(); k++) {
                            Element eventElement = (Element) randomEventNodes.item(k);
                            String probabilityStr = eventElement.getAttribute("probability");
                            int probability = Integer.parseInt(probabilityStr);
                            String eventText = eventElement.getTextContent().trim();
                            randomEvents.add(new RandomEvent(probability, eventText));
                        }

                        options.add(new Option(optionId, nextScene, optionText, randomEvents));
                    }

                    return new Scene(id, firstVisit, secondVisit, prompt, options);
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
