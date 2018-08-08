public class PlayerConsole extends PlayerCommon {
    public PlayerConsole(Integer id, String discType){
        super(id, discType);
    }

    @Override
    public TurnRecord makeTurn(Board board) {
        return null;
    }
}
