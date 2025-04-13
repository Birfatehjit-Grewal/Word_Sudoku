package sudoku.game;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class BoardComputationsTest {

    @Test
    public void checkBoard_isCorrect() {
        final int rows = 9;
        final int columns = 9;
        //test the return true statement
        String staticBoardInputCorrect = "2 9 5 7 4 3 8 6 1 " +
                "4 3 1 8 6 5 9 2 7 " +
                "8 7 6 1 9 2 5 4 3 " +
                "3 8 7 4 5 9 2 1 6 " +
                "6 1 2 3 8 7 4 9 5 " +
                "5 4 9 2 1 6 7 3 8 " +
                "7 6 3 5 2 4 1 8 9 " +
                "9 2 8 6 7 1 3 5 4 " +
                "1 5 4 9 3 8 6 7 2 ";

        //test the return false statement
        String staticBoardInputIncorrect = "2 9 5 7 4 3 8 6 1 " +
                "4 9 1 8 6 5 9 2 7 " +
                "8 7 6 1 9 2 5 4 3 " +
                "3 8 7 4 5 9 2 1 6 " +
                "6 1 2 3 8 7 4 9 5 " +
                "5 4 9 2 1 6 7 3 8 " +
                "7 6 3 5 2 4 1 8 9 " +
                "9 2 8 6 7 1 3 5 4 " +
                "1 5 4 9 3 8 6 7 2 ";

        String answerKey = "2 9 5 7 4 3 8 6 1 " +
                "4 3 1 8 6 5 9 2 7 " +
                "8 7 6 1 9 2 5 4 3 " +
                "3 8 7 4 5 9 2 1 6 " +
                "6 1 2 3 8 7 4 9 5 " +
                "5 4 9 2 1 6 7 3 8 " +
                "7 6 3 5 2 4 1 8 9 " +
                "9 2 8 6 7 1 3 5 4 " +
                "1 5 4 9 3 8 6 7 2 ";

        BoardComputations boardComputations = new BoardComputations(rows, columns);
        assertEquals(true, boardComputations.checkBoard(staticBoardInputCorrect, answerKey));
        assertEquals(false, boardComputations.checkBoard(staticBoardInputIncorrect, answerKey));
    }

    @Test
    public void checkRow_isCorrect() {
        String board = "2 9 ? 7 4 3 8 6 1 " +
                        "4 4 1 8 6 5 9 ? 7 " +
                        "8 7 6 1 9 2 5 4 3 " +
                        "3 8 7 4 5 9 2 1 6 " +
                        "6 1 2 3 ? 7 4 ? 5 " +
                        "? 4 9 2 ? 6 7 3 8 " +
                        "? ? 3 5 2 4 1 8 9 " +
                        "9 2 8 6 7 1 ? 5 4 " +
                        "1 5 4 9 3 ? 6 7 2 ";
        int incorrectRow = 1;
        int incorrectColumn = 1;
        int boardSize = 9;
        BoardComputations boardComputations = new BoardComputations(boardSize, boardSize);
        GridHelper GH = new GridHelper();
        int[][] intGrid = GH.makeIntGrid(board,boardSize,boardSize);
        assertEquals(0, boardComputations.checkRow(incorrectRow, incorrectColumn, boardSize, intGrid));

        int correctRow = 2;
        int correctColumn = 2;
        assertEquals(-1, boardComputations.checkRow(correctRow, correctColumn, boardSize, intGrid));
    }

    @Test
    public void checkColumn_isCorrect() {
        String board = "2 9 1 7 4 3 8 6 1 " +
                "4 4 1 8 6 5 9 3 7 " +
                "8 7 6 1 9 2 5 4 3 " +
                "3 8 7 4 9 9 2 1 6 " +
                "6 1 2 3 5 7 4 4 5 " +
                "5 4 9 2 5 6 7 3 8 " +
                "5 5 3 5 2 4 1 8 9 " +
                "9 2 8 6 7 1 1 5 4 " +
                "1 5 4 9 3 1 6 7 2 ";

        int incorrectRow = 3;
        int incorrectColumn = 4;
        int boardSize = 9;
        BoardComputations boardComputations = new BoardComputations(boardSize, boardSize);
        GridHelper GH = new GridHelper();
        int[][] intGrid = GH.makeIntGrid(board,boardSize,boardSize);
        assertEquals(2, boardComputations.checkColumn(incorrectRow, incorrectColumn, boardSize, intGrid));

        int correctRow = 8;
        int correctColumn = 2;
        assertEquals(-1, boardComputations.checkColumn(correctRow, correctColumn, boardSize, intGrid));

    }

    @Test
    public void checkCell_isCorrect() {
        String board = "2 9 4 7 4 3 8 6 1 " +
                "4 4 1 8 6 5 9 ? 7 " +
                "8 7 6 1 9 2 5 4 3 " +
                "3 8 7 4 5 9 2 1 6 " +
                "6 1 2 3 ? 7 4 ? 5 " +
                "? 4 9 2 ? 6 7 3 8 " +
                "? ? 3 5 2 4 1 8 9 " +
                "9 2 8 6 7 1 ? 5 4 " +
                "1 5 4 9 3 ? 6 7 2 ";

        int row = 1;
        int column = 1;
        int boardSize = 9;
        int cellWidth = 3;
        int cellHeight = 3;
        BoardComputations boardComputations = new BoardComputations(boardSize, boardSize);
        GridHelper GH = new GridHelper();
        int[][] intGrid = GH.makeIntGrid(board,boardSize,boardSize);
        int[] result = boardComputations.checkCell(row, column,cellWidth,cellHeight, boardSize, intGrid);
        assertEquals(0, result[0]);
        assertEquals(2, result[1]);

        int correctRow = 0;
        int correctColumn = 0;
        int[] correctResult = boardComputations.checkCell(correctRow, correctColumn,cellWidth,cellHeight, boardSize, intGrid);
        assertEquals(-1, correctResult[0]);
        assertEquals(-1, correctResult[1]);

    }

    @Test
    public void getGridSize_isCorrect() {
        String boardInfo = "Beginner/listening/9";
        BoardComputations boardComputations = new BoardComputations(boardInfo);
        assertEquals(9, boardComputations.getGridSize());
    }

    @Test
    public void getDifficultyMode_isCorrect() {
        String boardInfo = "Beginner/listening/9";
        BoardComputations boardComputations = new BoardComputations(boardInfo);
        assertEquals("Beginner", boardComputations.getDifficultyMode());
    }

    @Test
    public void getGameMode_isCorrect() {
        String boardInfo = "Beginner/reading/9";
        BoardComputations boardComputations = new BoardComputations(boardInfo);
        assertEquals(false, boardComputations.getGameMode());

        String boardInfoListening = "Beginner/listening/9";
        BoardComputations boardComputationsWithListening = new BoardComputations(boardInfoListening);
        assertEquals(true, boardComputationsWithListening.getGameMode());
    }
}