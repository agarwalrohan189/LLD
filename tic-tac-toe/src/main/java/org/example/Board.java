package org.example;

public class Board {
    private final int size;
    Cell [][] cells;

    public Board(int size) {
        this.size = size;
        cells = new Cell[size][size];
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                cells[row][col]=new Cell();
            }
        }
    }

    public boolean isCellFilled(int row, int col) throws GameException {
        if(validate(row, col)){
            Cell cell = getCell(row, col);
            return cell.getSymbol() != null;
        }
        return false;
    }

    public void fillCell(int row, int col, Symbol symbol) throws GameException {
        if(!isCellFilled(row, col)){
            cells[row][col].fillCell(symbol);
        }
    }

    public void printBoard(){
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                System.out.printf(" %s |", cells[row][col].printSymbol());
            }
            System.out.printf("\n");
            for(int col=0;col<size;col++){
                System.out.printf("-----", cells[row][col].printSymbol());
            }
            System.out.printf("\n");
        }
    }

    public boolean checkForMatch(int row, int col, Symbol symbol){
        boolean rowMatch = true, colMatch = true, diagonalMatch = (row==col), antiDiagonalMatch=(row+col==2*size-2);

        for(int i=0;i<size;i++){
            rowMatch = rowMatch && (cells[row][i].getSymbol()!=null && cells[row][i].getSymbol().equals(symbol));
            colMatch = colMatch && (cells[i][col].getSymbol()!=null && cells[i][col].getSymbol().equals(symbol));
            diagonalMatch = diagonalMatch && (cells[i][i].getSymbol()!=null && cells[i][i].getSymbol().equals(symbol));
            antiDiagonalMatch = antiDiagonalMatch && (cells[i][size-1-i].getSymbol()!=null && cells[i][size-1-i].getSymbol().equals(symbol));
        }

        return rowMatch||colMatch||diagonalMatch||antiDiagonalMatch;
    }

    public boolean isFilled(){
        boolean isFilled = true;
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                if(cells[row][col].getSymbol()==null) {
                    isFilled = false;
                    break;
                }
            }
        }
        return isFilled;
    }

    private Cell getCell(int row, int col){
        return cells[row][col];
    }

    private boolean validate(int row, int col) throws GameException {
        if(row>=0&&col>=0&&row<size&&col<size) return true;
        throw new GameException(500, String.format("Out of Bound Exception for row: %s and col %s", row, col));
    }
}
