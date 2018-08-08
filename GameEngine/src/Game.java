import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Game {
    private int target;
    private Board board;
    private ArrayList<Player> players;
    private Boolean isStarted;
    private Integer currentPlayerIndex;
    private Date startDate;

    public int getTarget() { return target; }

    public Board getBoard(){ return board; }

    public ArrayList<Player> getPlayers() { return players; }

    public Boolean getIsStarted() {
        return isStarted;
    }

    public Integer getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Game(){};

    public Game(int target, int rows, int columns){
        this.target = target;
        this.board = new Board(rows, columns);
        this.isStarted = false;
        this.currentPlayerIndex = 0;
        this.startDate = null;
    }

    public Game(int target, Board board){
        this.target = target;
        this.board = board;
        this.isStarted = false;
        this.currentPlayerIndex = 0;
        this.startDate = null;
    }

    private String getDurationString(Date currentDate) {
        long diffInSeconds = (currentDate.getTime() - this.startDate.getTime()) / 1000;
        long seconds = Math.floorMod(diffInSeconds, 60);
        long minutes = Math.floorDiv(diffInSeconds, 60);
        return String.format("%d:%d", minutes, seconds);
    }

    public void start(ArrayList<Player> players){
        this.players = players;
        this.board.addPlayers(this.players);


        this.isStarted = true;
        this.startDate = new Date();
    }

    @Override
    public String toString() {
        String playersBlock = "";
        for (Player player: this.players){
            playersBlock += player.toString();
        }

        String headerLine = String.format("Game of %d players, on a %dx%d board",
                this.players.size(),
                this.board.getRows(),
                this.board.getColumns());

        String fullFormat =
                "%s\n" +
                "------------------------------------\n" +
                "Game started: %s\n" +
                "Target: %d\n" +
                "Turn of: %d\n" +
                "%s" +
                "\n" +
                "////////////////  Board  ////////////////\n" +
                "%s\n\n" +
                "Time: %s\n" +
                "\n\n";

        String shortFormat =
                "%s\n" +
                "------------------------------------\n" +
                "Game started: %s\n" +
                "Target: %d\n" +
                "////////////////  Board  ////////////////\n" +
                "%s\n\n" +
                "\n\n";

        if (this.isStarted) {
            Date currentTime = new Date();
            String durationString = this.getDurationString(currentTime);
            return String.format(fullFormat,
                    headerLine,
                    this.isStarted.toString(),
                    this.target,
                    this.currentPlayerIndex,
                    playersBlock,
                    this.board,
                    durationString);
        } else {
            return String.format(shortFormat,
                    headerLine,
                    this.isStarted.toString(),
                    this.target,
                    this.board);
        }
    }
}
