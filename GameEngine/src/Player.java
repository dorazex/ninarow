public interface Player {
    Integer getTurnsCount();
    String getId();
    String getDiscType();
    TurnRecord makeTurn(Board board);
}
