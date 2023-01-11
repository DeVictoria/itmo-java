package Game_tic_tac_toe;

public class Exceptions {
    public static void getException(Move move, int length, int height){
        int gr = move.getRow()+1;
        int gc = move.getCol()+1;
        if ((0>=gr || gr>height) && (0>=gc || gc>length)){
            System.out.println("\nrow must be in [1, "+(height+1)+"] and column must be in [1, "+(length+1)+"] \ntry again\n");
        }
        else if (gc>length){
            System.out.println("\ncolumn must be < "+(length+1)+" \ntry again\n");
        }
        else if (0>=gc){
            System.out.println("\ncolumn must be > 0 \ntry again\n");
        }
        else if (gr>height){
            System.out.println("\nrow must be < "+(height+1)+" \ntry again\n");
        }
        else if (0>=gr){
            System.out.println("\nrow must be > 0 \ntry again\n");
        }
        else{
            System.out.println("\nposition occupied \ntry again\n");
        }
    }
}
