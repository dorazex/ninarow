import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ConsoleUserInterface {
    private Game game;
    private Integer command;
    private Scanner scanner;

    public Game getGame() {
        return game;
    }

    public Integer getCommand() {
        return command;
    }

    private String getInput(String instructionMessage){
        if (instructionMessage!=null){
            System.out.print(instructionMessage);
        }

        return scanner.nextLine();
    }

    private Integer parseCommand(){
        try {
            return Integer.parseInt(this.getInput(Constants.CHOOSE_COMMAND_PROMPT));
        } catch (NumberFormatException e){
            System.out.println(Constants.INVALID_INPUT_PROMPT);
            return this.parseCommand();
        }
    }

    public ConsoleUserInterface(){
        this.game = null;
        this.command = -1;
        this.scanner = new Scanner(System.in);
    }

    private void createGame(String configFilePath) throws ConfigXmlException{
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
            PlayerConsole playerConsole = new PlayerConsole(playerConsoleIndex, "X", this.scanner);
            PlayerComputer playerComputer = new PlayerComputer(playerComputerIndex, "O");
            players.add(playerComputer);
            players.add(playerConsole);
        } else {
            playerConsoleIndex = 1;
            PlayerConsole playerConsole = new PlayerConsole(playerConsoleIndex, "X", this.scanner);
            PlayerComputer playerComputer = new PlayerComputer(playerComputerIndex, "O");
            players.add(playerConsole);
            players.add(playerComputer);
        }
        return players;
    }

    public void startUI(){
        System.out.println(Constants.START_PROMPT);
        Boolean isGameLoaded = false;
        Boolean isGameEnded = false;

        while (!this.command.equals(Constants.COMMAND_EXIT) && !isGameEnded){

            this.command = this.parseCommand();

            switch (this.command){
                case Constants.COMMAND_LOAD: // Load game from XML
                    if (isGameLoaded && this.game.getIsStarted()){
                        System.out.println(Constants.INVALID_COMMAND_GAME_ALREADY_STARTED);
                    } else {
                        try {
                            createGame(this.getInput(Constants.CONFIG_FILE_PATH_PROMPT));
                            System.out.println(this.game.toString());
                            isGameLoaded = true;
                        } catch (ConfigXmlException e) {
                            System.out.print(Constants.CONFIG_XML_ERROR);
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case Constants.COMMAND_START: // Start game
                    if (!isGameLoaded){
                        System.out.println(Constants.INVALID_COMMAND_GAME_NOT_LOADED);
                    } else if (this.game.getIsStarted()){
                        System.out.println(Constants.INVALID_COMMAND_GAME_ALREADY_STARTED);
                    } else{
                        this.game.start(this.generatePlayers());
                        System.out.println(Constants.GAME_SUCCESSFULLY_STARTED_PROMPT);
                    }
                    break;
                case Constants.COMMAND_SHOW: // Show game
                    if (!isGameLoaded){
                        System.out.println(Constants.INVALID_COMMAND_GAME_NOT_LOADED);
                    } else {
                        System.out.println(this.game.toString());
                    }
                    break;
                case Constants.COMMAND_TURN: // Make turn
                    if (!isGameLoaded){
                        System.out.println(Constants.INVALID_COMMAND_GAME_NOT_LOADED);
                    } else if(!this.game.getIsStarted()){
                        System.out.println(Constants.INVALID_COMMAND_GAME_NOT_STARTED);
                    } else {
//                        if (this.game.getPlayers().get(this.game.getCurrentPlayerIndex())
//                                .getClass().getName().contains("Console")) {
//                            System.out.println(this.game.toString());
//                        }
                        isGameEnded = this.game.makeTurn();
                        if (!isGameEnded) {
                            System.out.println(this.game.toString());
                        } else {
                            isGameEnded = true;
                        }
                    }
                    break;
                case Constants.COMMAND_HISTORY: // History
                    if (!isGameLoaded){
                        System.out.println(Constants.INVALID_COMMAND_GAME_NOT_LOADED);
                    } else {
                        System.out.print(this.game.getHistory());
                    }
                    break;
                case Constants.COMMAND_EXIT:
                    System.out.println(Constants.EXIT_PROMPT);
                    break;
                default:
                    System.out.println(Constants.INVALID_INPUT_PROMPT);
                    break;
            }

            if (isGameEnded){
                Player winner = this.game.getWinnerPlayer();
                if (winner != null){
                    System.out.print("\n\n THE WINNER IS: " + winner.getId() + "!\n");
                } else {
                    System.out.println("\n\n GAME ENDED IN A TIE!\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("ConsoleUserInterface");
    }
}
