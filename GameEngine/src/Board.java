public class Board {
    private int rows;
    private int columns;
    private Integer[][] cells;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Integer[][] getCells() {
        return cells;
    }

    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.cells = new Integer[rows][columns];
    }
}
