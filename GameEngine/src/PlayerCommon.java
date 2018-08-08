public abstract class PlayerCommon implements Player {
    private Integer turnsCount;
    private String id;
    private String discType;

    public PlayerCommon(String id, String discType){
        this.turnsCount = 0;
        this.id = id;
        this.discType = discType;
    }

    @Override
    public Integer getTurnsCount() {
        return this.turnsCount;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getDiscType() {
        return this.discType;
    }

    @Override
    public String toString() {
        return String.format("Player '%s': disc=%s, turns=%d\n", this.id, this.discType, this.turnsCount);
    }
}
