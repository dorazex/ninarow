import java.util.Scanner;

public class PlayerConsole extends PlayerCommon {
    private Scanner scanner;

    public PlayerConsole(Integer id, String discType, Scanner scanner){
        super(id, discType);
        this.scanner = scanner;
    }

    @Override
    public TurnRecord makeTurn(Board board) {
        System.out.print("Please choose column for disc (then hit <Enter>): ");
        Integer chosenColumn = Integer.parseInt(this.scanner.nextLine()) - 1;
        TurnRecord turnRecord = board.putDisc(this, chosenColumn);
        if (turnRecord == null){
            System.out.println("Chosen column is full. Please play again");
            return this.makeTurn(board);
        }
        this.turnsCount += 1;
        return turnRecord;
    }
}
