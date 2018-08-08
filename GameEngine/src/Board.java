import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private int rows;
    private int columns;
    private HashMap<Integer, String> playersDiscTypeMap;
    private ArrayList<ArrayList<Integer>> cells;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList<ArrayList<Integer>> getCells() {
        return cells;
    }

    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;

        this.playersDiscTypeMap = new HashMap<>();
        this.playersDiscTypeMap.put(0, "_");

        this.clearBoard();
    }

    private void clearBoard(){
        this.cells = new ArrayList<>();
        for (int i = 0; i < this.columns; i++) {
            ArrayList<Integer> column = new ArrayList<Integer>();
            for (int j = 0; j < this.rows; j++) {
                column.add(0);
            }
            this.cells.add(column);
        }
    }

    private Boolean canInsert(Integer column){
        return !this.getAvailableIndexInColumn(column).equals(-1);
    }

    private Integer getAvailableIndexInColumn(int column){
        return this.cells.get(column).lastIndexOf(0);
    }

    private Integer countAvailableCells(){
        Integer count = 0;
        for (ArrayList<Integer> column: this.cells){
            for (Integer cellContent: column){
                if (cellContent.equals(0)) count+=1;
            }
        }
        return count;
    }

    private static Boolean isTargetInSequence(String sequence, Integer target){
        return sequence.matches(String.format("[^0]{%d}", target));
    }

    private Integer getCellContent(Integer row, Integer column){
        return this.cells.get(column).get(row);
    }

    public Boolean isTargetReached(Integer target){
        String sequenceToCheck = "";
        for (int i = 0; i < this.rows; i++) {
            sequenceToCheck = "";
            for (ArrayList<Integer> column: this.cells){
                sequenceToCheck += column.get(i);
            }
            if (Board.isTargetInSequence(sequenceToCheck, target)) return true;
        }

        for (ArrayList<Integer> column: this.cells){
            sequenceToCheck = "";
            for (Integer cellContent: column){
                sequenceToCheck += String.format("%d", cellContent);
            }
            if (Board.isTargetInSequence(sequenceToCheck, target)) return true;
        }



        return false;
    }

    public Boolean isFull(){
        return this.countAvailableCells().equals(0);
    }

    public void addPlayers(ArrayList<Player> players){
        for (Player player: players){
            this.playersDiscTypeMap.put(player.getId() + 1, player.getDiscType());
        }
    }

    public TurnRecord putDisc(Player player, int column){
        TurnRecord turnRecord = null;
        if (this.canInsert(column)){
            this.cells.get(column).set(this.getAvailableIndexInColumn(column), player.getId() + 1);
            turnRecord = new TurnRecord(player, column);
        }
        return turnRecord;
    }

    @Override
    public String toString() {
        String boardString = "";
        for (int i = 0; i < this.rows; i++) {
            boardString += String.format("%d ", this.rows - i);
            for (ArrayList<Integer> column: this.cells){
                boardString += this.playersDiscTypeMap.get(column.get(i)) + " ";
            }
            boardString += "\n";
        }
        boardString += "  ";
        for (int i = 0; i < this.columns; i++) {
            boardString += String.format("%d ", i + 1);
        }

        return boardString;
    }
}
