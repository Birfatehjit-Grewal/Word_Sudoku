package sudoku.game;

public class BoardComputations {
    private int rows;
    private int columns;
    private int gridSize;
    private String difficultyMode;
    private String gameMode;

    public BoardComputations(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public BoardComputations(String boardInfo) {
        String[] tokens = boardInfo.split("/");
        this.gridSize = Integer.parseInt(tokens[2]);
        this.difficultyMode = tokens[0];
        this.gameMode = tokens[1];
    }
    // checks the board and sees if the board if filled correctly by comparing with the answer key
    // returns true if the board is filled correctly and false otherwise
    public boolean checkBoard(String filledBoard, String answerKey) {
        String[] splitBoard = filledBoard.split(" ");
        String[] splitKey = answerKey.split(" ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (splitBoard[i * rows + j].charAt(0) != splitKey[i * rows + j].charAt(0)) {
                    return false;
                }
            }
        }
        return true;
    }
    //checks the row
    public int checkRow(int row,int column,int size,int[][] intBoard){
        for(int i = 0;i<size;i++){
            if(i!=column){
                if(intBoard[row][column]==intBoard[row][i] && intBoard[row][column]!= 0){
                    return i;
                }
            }
        }
        return -1;
    }
    //checks the column
    public int checkColumn(int row,int column,int size,int[][] intBoard){
        for(int i = 0;i<size;i++){
            if(i!=row){
                if(intBoard[row][column]==intBoard[i][column] && intBoard[row][column]!= 0){
                    return i;
                }
            }
        }
        return -1;
    }
    //checks the cell
    public int[] checkCell(int row,int column,int CellWidth, int CellHeight,int size,int[][] intBoard){
        int[] check = new int[2];
        check[1] = -1;
        check[0] = -1;
        int tmp1 = row/CellHeight;
        int tmp2 = column/CellWidth;

        for(int i = 0;i<CellHeight;i++){
            for(int j = 0;j<CellWidth;j++){
                if((tmp1*CellHeight + i)!=row && (tmp2*CellWidth + j)!=column){
                    if(intBoard[row][column]==intBoard[tmp1*CellHeight + i][tmp2*CellWidth + j] && intBoard[row][column]!= 0){
                        check[1] = tmp2*CellWidth + j;
                        check[0] = tmp1*CellHeight + i;
                        return check;
                    }
                }
            }
        }
        return check;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public String getDifficultyMode() {
        return this.difficultyMode;
    }

    public Boolean getGameMode() {
        if(this.gameMode.matches("listening")){
            return true;
        }
        return false;
    }
}
