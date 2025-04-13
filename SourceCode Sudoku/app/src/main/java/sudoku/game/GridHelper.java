package sudoku.game;

public class GridHelper {
    //returns the int matrix for the board
    public int[][] makeIntGrid(String board,int row, int column){
        int[][] intvalues = new int[row][column];
        String[] split = board.split(" ");
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                String wordIndex = split[i * row + j];
                if(wordIndex.equals("?")){
                    intvalues[i][j] = 0;
                }
                else if(wordIndex.equals("1")){
                    intvalues[i][j] = 1;
                }
                else if(wordIndex.equals("2")){
                    intvalues[i][j] = 2;
                }
                else if(wordIndex.equals("3")){
                    intvalues[i][j] = 3;
                }
                else if(wordIndex.equals("4")){
                    intvalues[i][j] = 4;
                }
                if(wordIndex.equals("5")){
                    intvalues[i][j] = 5;
                }
                else if(wordIndex.equals("6")){
                    intvalues[i][j] = 6;
                }
                else if(wordIndex.equals("7")){
                    intvalues[i][j] = 7;
                }
                else if(wordIndex.equals("8")){
                    intvalues[i][j] = 8;
                }
                else if(wordIndex.equals("9")){
                    intvalues[i][j] = 9;
                }
                else if(wordIndex.equals("10")){
                    intvalues[i][j] = 10;
                }
                else if(wordIndex.equals("11")){
                    intvalues[i][j] = 11;
                }
                else if(wordIndex.equals("12")){
                    intvalues[i][j] = 12;
                }
            }
        }
        return intvalues;
    }
    //returns the boolean matrix for the given grid
    public boolean[][] makeGivenGrid(String board,int row, int column){
        boolean[][] givenvalue = new boolean[row][column];
        String[] split = board.split(" ");
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                String wordIndex = split[i * row + j];
                Character cIndex = wordIndex.charAt(0);
                if(cIndex == '?'){
                    givenvalue[i][j] = false;
                }
                else{
                    givenvalue[i][j] = true;
                }
            }
        }
        return givenvalue;
    }
}
