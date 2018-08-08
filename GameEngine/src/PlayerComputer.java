public class PlayerComputer extends PlayerCommon {
    public PlayerComputer(Integer id, String discType){
        super(id, discType);
    }

    @Override
    public TurnRecord makeTurn(Board board) {
        return null;
    }
}
