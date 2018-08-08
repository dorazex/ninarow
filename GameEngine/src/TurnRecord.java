public class TurnRecord {
    private Player player;
    private int column;

    public Player getPlayer() {
        return player;
    }

    public int getColumn() {
        return column;
    }

    public TurnRecord(Player player, int column){
        this.player = player;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("Player <%s> have put a disc of type <%s> at column <%d>",
                this.player.getId(),
                this.player.getDiscType(),
                this.column);
    }
}
