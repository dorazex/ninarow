public interface Player {
    String getId();
    String getDiscType();
    TurnRecord makeTurn(Board board);
}
