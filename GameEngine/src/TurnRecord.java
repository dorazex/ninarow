public class TurnRecord {
    private Player player;
    private int row;

    public Player getPlayer() {
        return player;
    }

    public int getRow() {
        return row;
    }

    public TurnRecord(Player player, int row){
        this.player = player;
        this.row = row;
    }

    @Override
    public String toString() {
        return String.format("Player <%s> have put a disc of type <%s> at row <%d>",
                this.player.getId(),
                this.player.getDiscType(),
                this.row);
    }
}
