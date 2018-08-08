public class PlayerComputer extends PlayerCommon {
    public PlayerComputer(String id, String discType){
        super(id, discType);
    }

    @Override
    public TurnRecord makeTurn(Board board) {
        return null;
    }
}
