package Game;

public class Game {
    private  Player player1;
    private final Player player2;
    int result2 =-1;
    int result1 =-1;
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public String play(final TicTacToeBoard board) {
        while(true) {
            if (result2!=-10) {
                result1 = makeMove(board, player1, 1);
            }
            if (result1 > 0) {
                player1.addWin();
                return board +"\n"+"Player "+ player1.getNumber()+" win";
            }
            else if(result1 == 0){
                return board +"\n"+"Drow";
            }
            else if(result1==-10){
                continue;
            }
            result2 = makeMove(board, player2, 2);
            if (result2 > 0) {
                player2.addWin();
                return board +"\n"+"Player "+ player2.getNumber()+" win";
            }
            else if(result2 == 0){
                return board +"\n"+"Drow";
            }
        }
    }
    public int makeMove(TicTacToeBoard board, Player player,int no){
        Move move=player.move(board.getPosition());
        Result result = board.makeMove(move);
        System.out.printf("\nPlayer #"+player.getNumber()+", move: (%d, %d, %s)%n", move.getRow()+1, move.getCol()+1, move.getCell());
        if (result == Result.WIN){
            return no;
        }
        else if (result == Result.DRAW){
            return 0;
        }
        else if (result == Result.LOSE){
            return 3-no;
        }
        else if (result == Result.NEWMOVE){
            return -10;
        }
        else{
            return -1;
        }
    }
}
