import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started running");

        String configFilePath = "/home/duke/Downloads/ex1-small.xml";
        HashMap<String, Object> parametersMap = XmlLoader.getGameBasicInitParameters(configFilePath);
        String variant = (String) parametersMap.get("variant");
        Integer target = (Integer) parametersMap.get("target");
        Integer rows = (Integer) parametersMap.get("rows");
        Integer columns = (Integer) parametersMap.get("columns");

        System.out.println("Loaded XML");

        Game game = new Game(target, rows, columns, new ArrayList<Player>());
        System.out.println(game);
    }
}
