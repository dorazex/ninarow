public class Constants {
    public static final int COMMAND_LOAD = 1;
    public static final int COMMAND_START = 2;
    public static final int COMMAND_SHOW = 3;
    public static final int COMMAND_TURN = 4;
    public static final int COMMAND_HISTORY = 5;
    public static final int COMMAND_EXIT = 6;

    public static final String START_PROMPT = "Welcome to the game!";
    public static final String EXIT_PROMPT = "Bye bye!";
    public static final String CHOOSE_COMMAND_PROMPT = "Please enter command: ";
    public static final String INVALID_INPUT_PROMPT = "Command must be an integer between 1-6, then <Enter>";
    public static final String GAME_SUCCESSFULLY_STARTED_PROMPT = "Game started successfully";
    public static final String CONFIG_FILE_PATH_PROMPT = "Please enter FULL ABSOLUTE path for config XML file: ";

    public static final String INVALID_COMMAND_GAME_ALREADY_STARTED = "Game has already started";
    public static final String INVALID_COMMAND_GAME_NOT_LOADED = "No game loaded yet";
    public static final String INVALID_COMMAND_GAME_NOT_STARTED = "Game not started yet";
    public static final String CONFIG_XML_ERROR = "Invalid config XML file given: ";
}
