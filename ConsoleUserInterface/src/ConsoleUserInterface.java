import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ConsoleUserInterface {
    private Game game;
    private Integer command;
    private String inputString;
    private Scanner scanner;

    public Game getGame() {
        return game;
    }

    public Integer getCommand() {
        return command;
    }

    private void getInput(String instructionMessage){
        if (instructionMessage!=null){
            System.out.println(instructionMessage);
        }

        this.inputString = scanner.nextLine();
    }

    private Integer parseCommand(){
        try {
            return Integer.parseInt(this.inputString);
        } catch (NumberFormatException e){
            System.out.println(Constants.INVALID_INPUT_PROMPT);
            this.getInput(Constants.CHOOSE_COMMAND_PROMPT);
            return this.parseCommand();
        }
    }

    public ConsoleUserInterface(){
        this.game = null;
        this.command = -1;
        this.inputString = "";
        this.scanner = new Scanner(System.in);
    }

    private void createGame(String configFilePath){
//        configFilePath = "/home/duke/Downloads/ex1-small.xml";

        HashMap<String, Object> parametersMap = XmlLoader.getGameBasicInitParameters(configFilePath);
        String variant = (String) parametersMap.get("variant");
        Integer target = (Integer) parametersMap.get("target");
        Integer rows = (Integer) parametersMap.get("rows");
        Integer columns = (Integer) parametersMap.get("columns");

        this.game = new Game(target, rows, columns);
    }

    private ArrayList<Player> generatePlayers(){
        ArrayList<Player> players = new ArrayList<>();

        Integer playerConsoleIndex;
        Integer playerComputerIndex = new Random().nextInt(2) + 1;
        if (playerComputerIndex.equals(1)){
            playerConsoleIndex = 2;
        } else {
            playerConsoleIndex = 1;
        }

        PlayerConsole playerConsole = new PlayerConsole(playerConsoleIndex, "X");
        PlayerComputer playerComputer = new PlayerComputer(playerComputerIndex, "O");
        players.add(playerConsole);
        players.add(playerComputer);
        return players;
    }

    public void startUI(){
        System.out.println(Constants.START_PROMPT);
        while (!this.command.equals(Constants.COMMAND_EXIT)){
            switch (this.command){
                case Constants.COMMAND_LOAD: // Load game from XML
                    this.getInput(Constants.CONFIG_FILE_PATH_PROMPT);
                    createGame(this.inputString);
                    System.out.println(Constants.GAME_LOADED_PROMPT);
                    break;
                case Constants.COMMAND_START: // Start game
                    if (this.game.getIsStarted()){
                        System.out.println(Constants.INVALID_COMMAND_GAME_ALREADY_STARTED);
                    } else{
                        this.game.start(this.generatePlayers());
                    }
                    break;
                case Constants.COMMAND_SHOW: // Show game
                    System.out.println(this.game.toString());
                    break;
                case Constants.COMMAND_TURN: // Make turn
                    break;
                case Constants.COMMAND_HISTORY: // History
                    break;
                default:
                    break;
            }




            // Get next command
            this.getInput(Constants.CHOOSE_COMMAND_PROMPT);
            this.command = this.parseCommand();
        }
        System.out.println(Constants.EXIT_PROMPT);
    }

    public static void main(String[] args) {
        System.out.println("ConsoleUserInterface");
    }
}
