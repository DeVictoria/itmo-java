package Game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private final Cell[][] field;
    private final Map<Cell, Character> CELL_TO_STRING = Map.of(Cell.X, 'X', Cell.O, 'O', Cell.E, '.');
    int length;
    int height;
    int ltw;
    private Cell turn;
    
    public TicTacToeBoard(int height, int length, int ltw) {
        this.length = length;
        this.height = height;
        this.ltw = ltw;
        field = new Cell[height][length];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }
    
    @Override
    public Position getPosition() {
        return this;
    }

    public Cell getCell() {
        return turn;
    }

    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        field[move.getRow()][move.getCol()] = move.getCell();
        int z = check(1, 0, move.getRow(), move.getCol(), move.getCell());
        z += check(0, 1, move.getRow(), move.getCol(), move.getCell());
        z += check(1, 1, move.getRow(), move.getCol(), move.getCell());
        z += check(1, -1, move.getRow(), move.getCol(), move.getCell());
        if (z > 0 & z < 5) {
            return Result.WIN;
        } else if (z >= 4) {
            return Result.NEWMOVE;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        int empty = 0;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < length; c++) {
                if (field[r][c] == Cell.E) {
                    empty++;
                }
            }
        }
        return empty > 0 ? Result.UNKNOWN : Result.DRAW;
    }
    
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < height && 0 <= move.getCol() && move.getCol() < length && field[move.getRow()][move.getCol()] == Cell.E && move.getCell() == turn;
    }
    
    public int check(int op1, int op2, int Row, int Col, Cell Cell) {
        int i = 2;
        int count = 0;
        int posit1 = Row;
        int posit2 = Col;
        while (i > 0 & count != ltw) {
            for (int j = 0; j < 2; j++) {
                if (posit1 > height - 1 | posit1 < 0 | posit2 > length - 1 | posit2 < 0) {
                    i--;
                    posit1 = Row - op1;
                    posit2 = Col - op2;
                }
            }
            if (i <= 0) {
                break;
            }
            if (field[posit1][posit2] == Cell) {
                if ((op1 == 0 ? posit2 : posit1) >= (op1 == 0 ? Col : Row)) {
                    posit1 += op1;
                    posit2 += op2;
                } else {
                    posit1 -= op1;
                    posit2 -= op2;
                }
                count++;
            } else {
                i--;
                posit1 = Row - op1;
                posit2 = Col - op2;
            }
        }
        System.out.println(count);
        if (count == ltw) {
            return 1;
        } else if (count == 4) {
            return 5;
        } else {
            return 0;
        }
    }
    
    @Override
    public Cell getTurn() {
        return turn;
    }
    
    @Override
    public int getLength() {
        return length;
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public int getLtw() {
        return ltw;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("  ");
        for (int c = 1; c <= length; c++) {
            sb.append(c);
            sb.append(" ");
        }
        sb.append("\n");
        for (int r = 0; r < height; r++) {
            sb.append(r + 1)
                    .append("|");
            for (int c = 0; c < length; c++) {
                sb.append(CELL_TO_STRING.get(field[r][c]));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
