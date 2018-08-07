import java.util.ArrayList;

public class Game {
    private int target;
    private Board board;
    private ArrayList<Player> players;

    public int getTarget() { return target; }

    public Board getBoard(){ return board; }

    public ArrayList<Player> getPlayers() { return players; }

    public Game(){};

    public Game(int target, int rows, int columns, ArrayList<Player> players){
        this.target = target;
        this.board = new Board(rows, columns);
        this.players = players;
    }

    public Game(int target, Board board, ArrayList<Player> players){
        this.target = target;
        this.board = board;
        this.players = players;
    }

    @Override
    public String toString() {
        return String.format("Game of %d players, on a %dx%d board, with a %d-in-a-row target",
                this.players.size(),
                this.board.getRows(),
                this.board.getColumns(),
                this.target);
    }
}
