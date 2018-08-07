import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;


public class XmlLoader {
    public static HashMap<String, Object> getGameBasicInitParameters(String filePath) {

        HashMap<String, Object> parametersMap = new HashMap<>();
        try {

            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

            NodeList gameNodeList = doc.getElementsByTagName("Game");
            Element gameElement = (Element) gameNodeList.item(0);
            Element boardElement = (Element) gameElement.getElementsByTagName("Board").item(0);
            Element variantElement = (Element) gameElement.getElementsByTagName("Variant").item(0);

            String variant = variantElement.getTextContent();
            String target = gameElement.getAttribute("target");
            String rows = boardElement.getAttribute("rows");
            String columns = boardElement.getAttribute("columns");

//            System.out.println(variant);
//            System.out.println(target);
//            System.out.println(rows);
//            System.out.println(columns);
//
//            System.out.println("----------------------------");

            parametersMap.put("variant", variant);
            parametersMap.put("target", Integer.parseInt(target));
            parametersMap.put("rows", Integer.parseInt(rows));
            parametersMap.put("columns", Integer.parseInt(columns));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return parametersMap;
    }

}
